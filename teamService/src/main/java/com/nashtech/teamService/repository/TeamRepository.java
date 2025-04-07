package com.nashtech.teamService.repository;

import com.nashtech.teamService.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Team entities.
 * <p>
 * This interface provides CRUD operations for the {@link Team} entity
 * using Spring Data JPA.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
