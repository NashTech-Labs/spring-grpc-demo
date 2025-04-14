
package com.nashtech.teamService.service;

import com.nashtech.teamService.entities.Team;

import java.util.List;

/**
 * Service interface for managing teams.
 * <p>
 * This interface defines methods for creating and retrieving teams
 * in the system.
 * </p>
 * @author [Nadra Ibrahim]
 */
public interface TeamService {

    public Team createTeam(Team team);
    public Team getOne(Long teamId);
    public List<Team> getAll();
    public void deleteTeam(Long teamId);
}
