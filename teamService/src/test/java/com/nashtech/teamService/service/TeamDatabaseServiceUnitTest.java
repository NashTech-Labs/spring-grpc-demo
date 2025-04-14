package com.nashtech.teamService.service;

import com.nashtech.teamService.entities.Player;
import com.nashtech.teamService.entities.Team;
import com.nashtech.teamService.repository.TeamRepository;
import com.nashtech.teamService.service.implementation.TeamDatabaseServiceImpl;
import com.nashtech.teamService.service.implementation.TeamGrpcClientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamDatabaseServiceUnitTest {

    @Mock
    TeamRepository teamRepository;
    @InjectMocks
    TeamDatabaseServiceImpl teamDatabaseServiceImpl;
    @Mock
    TeamGrpcClientService teamGrpcClientService;
    private static Team team= null;
    private static Long teamId= 1L;

    @BeforeAll
    public static void init(){
        System.out.println("BeforeAll");
        team = new Team();
        team.setTeamId(1L);
        team.setTeamName("Avengers");
    }

    @Test
    void createTeamShouldCreateTeamSuccessfully(){
        // Arrange
        when(teamRepository.save(team)).thenReturn(team);
        // Act
        Team addedTeam = teamDatabaseServiceImpl.createTeam(team);
        // Assert
        assertNotNull(addedTeam);
        assertEquals(team.getTeamId(),addedTeam.getTeamId());
        assertEquals(team.getTeamName(),addedTeam.getTeamName());
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void getOneShouldRetrieveTheTeamWithPlayers() {
        // Arrange
        List<Player> players = List.of(new Player(1L, "Iron Man", teamId), new Player(2L, "Hulk", teamId));
        team.setPlayers(players);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(teamGrpcClientService.getPlayersOfTeam(teamId)).thenReturn(players);
        // Act
        Team result = teamDatabaseServiceImpl.getOne(teamId);
        // Assert
        assertNotNull(result);
        assertEquals("Avengers", result.getTeamName());
        assertEquals(2, result.getPlayers().size());
        assertEquals("Iron Man", result.getPlayers().get(0).getPlayerName());
        verify(teamRepository, times(1)).findById(teamId);
    }

    @Test
    void getOneShouldThrowTeamNotFoundException() {
        // Arrange
        Long teamId = 100L;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            teamDatabaseServiceImpl.getOne(teamId);
        });

        assertEquals("Team not found.", exception.getMessage());
        verify(teamRepository, times(1)).findById(teamId);
    }


    @Test
    void getAllShouldRetrieveAllTheTeamsWithPlayers() {
        // Arrange
        List<Player> firstTeamPlayers = List.of(new Player(6L, "Sunil", 3L), new Player(7L, "Sachin", 3L));
        team.setPlayers(firstTeamPlayers);
        List<Team> teams = List.of(new Team(3L, "BasketBall", firstTeamPlayers), new Team(4L, "Badminton", new ArrayList<Player>()));
        when(teamRepository.findAll()).thenReturn(teams);
        when(teamGrpcClientService.getPlayersOfTeam(3L)).thenReturn(firstTeamPlayers);
        // Act
        List<Team> listOfTeams = teamDatabaseServiceImpl.getAll();
        // Assert
        assertNotNull(listOfTeams);
        assertEquals(2, listOfTeams.size());
        assertEquals(3, listOfTeams.get(0).getTeamId());
        assertEquals("BasketBall", listOfTeams.get(0).getTeamName());
        assertEquals(2, listOfTeams.get(0).getPlayers().size());
        assertEquals("Sunil", listOfTeams.get(0).getPlayers().get(0).getPlayerName());
        verify(teamRepository, times(1)).findAll();
    }


}
