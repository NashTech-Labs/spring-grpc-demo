package com.nashtech.playerService.repository;

import com.nashtech.playerService.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repository interface for managing Player entities.
 * <p>
 * This interface provides CRUD operations for the {@link Player} entity
 * using Spring Data JPA.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
}
