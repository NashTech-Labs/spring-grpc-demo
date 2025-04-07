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
    public Team create(@RequestBody Team team){
        return teamService.create(team);
    }

    @GetMapping("/{teamId}")
    public Team getOne(@PathVariable Long teamId){
        return teamService.getOne(teamId);
    }

    @GetMapping
    public List<Team> getAll(){
        return teamService.getAll();
    }
}


