package com.nashtech.playerService.service;
import com.nashtech.playerService.entity.Player;

import java.util.List;


/**
 * Service interface for managing players.
 * <p>
 * This interface defines methods for creating and retrieving players
 * in the system.
 * </p>
 * @author [Nadra Ibrahim]
 */
public interface PlayerDatabaseService {
    public Player create(Player player);
    public Player getOne(Long playerId);
    public List<Player> getAll();
}
