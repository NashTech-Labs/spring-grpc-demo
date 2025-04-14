package com.nashtech.teamService.service.implementation;

import com.nashtech.teamService.entities.Team;
import com.nashtech.teamService.repository.TeamRepository;
import com.nashtech.teamService.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link TeamService} interface.
 * <p>
 * This class provides concrete implementations of methods for managing
 * team data using JPA.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@Service
public class TeamDatabaseServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    private TeamGrpcClientService teamGrpcClientService;

    public TeamDatabaseServiceImpl(TeamRepository teamRepository, TeamGrpcClientService teamGrpcClientService) {
        this.teamRepository = teamRepository;
        this.teamGrpcClientService = teamGrpcClientService;
    }


    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getOne(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(()-> new RuntimeException("Team not found."));
        team.setPlayers(teamGrpcClientService.getPlayersOfTeam(team.getTeamId()));
        return team;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = teamRepository.findAll();
        List<Team> teamListWithPlayers = teams.stream().map(team -> {
            team.setPlayers(teamGrpcClientService.getPlayersOfTeam(team.getTeamId()));
            return team;
        }).collect(Collectors.toList());
        return teamListWithPlayers;
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}

