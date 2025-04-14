package com.nashtech.teamService.controller;

import com.nashtech.teamService.entities.Team;
import com.nashtech.teamService.service.TeamDatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for handling team-related operations.
 * <p>
 * This controller provides endpoints for creating and retrieving teams.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@RestController
@RequestMapping("/teams")
public class TeamController {
    private TeamDatabaseService teamDatabaseService;

    public TeamController(TeamDatabaseService teamDatabaseService) {
        this.teamDatabaseService = teamDatabaseService;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team){
        return teamDatabaseService.createTeam(team);
    }

    @GetMapping("/{teamId}")
    public Team getSingleTeam(@PathVariable Long teamId){
        return teamDatabaseService.getOne(teamId);
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return teamDatabaseService.getAll();
    }

}


