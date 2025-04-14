package com.nashtech.teamService.service.implementation;

import com.nashtech.teamService.entities.Team;
import com.nashtech.teamService.repository.TeamRepository;
import com.nashtech.teamService.service.TeamDatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link TeamDatabaseService} interface.
 * <p>
 * This class provides concrete implementations of methods for managing
 * team data using JPA.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@Service
public class TeamDatabaseServiceImpl implements TeamDatabaseService {

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
        return teams.stream().peek(team -> team.setPlayers(teamGrpcClientService.getPlayersOfTeam(team.getTeamId()))).collect(Collectors.toList());
    }
}

