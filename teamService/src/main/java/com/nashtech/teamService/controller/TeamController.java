package com.nashtech.teamService.controller;

import com.nashtech.teamService.entities.Team;
import com.nashtech.teamService.service.TeamService;
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
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team){
        return teamService.createTeam(team);
    }

    @GetMapping("/{teamId}")
    public Team getSingleTeam(@PathVariable Long teamId){
        return teamService.getOne(teamId);
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.getAll();
    }

    @DeleteMapping
    public void deleteTeam(@PathVariable Long teamId){
        teamService.deleteTeam(teamId);
    }
}


