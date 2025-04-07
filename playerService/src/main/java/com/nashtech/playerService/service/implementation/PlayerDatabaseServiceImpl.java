package com.nashtech.playerService.service.implementation;

import com.nashtech.playerService.entity.Player;
import com.nashtech.playerService.repository.PlayerRepository;
import com.nashtech.playerService.service.PlayerDatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of the {@link PlayerDatabaseService} interface.
 * <p>
 * This class provides concrete implementations of methods for managing
 * player data using JPA.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@Service
public class PlayerDatabaseServiceImpl implements PlayerDatabaseService {

    private PlayerRepository playerRepository;

    public PlayerDatabaseServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getOne(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(()-> new RuntimeException("Player not found."));
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }
}
