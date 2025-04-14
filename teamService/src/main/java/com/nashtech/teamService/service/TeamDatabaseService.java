
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
public interface TeamDatabaseService {

    Team createTeam(Team team);
    Team getOne(Long teamId);
    List<Team> getAll();
}
