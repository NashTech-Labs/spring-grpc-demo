package com.nashtech.playerService.controller;

import com.nashtech.playerService.entity.Player;
import com.nashtech.playerService.service.PlayerDatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling player-related operations.
 * <p>
 * This controller provides endpoints for creating and retrieving players.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerDatabaseService playerDatabaseService;

    public PlayerController(PlayerDatabaseService playerServiceRest) {
        this.playerDatabaseService = playerServiceRest;
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player){
        return playerDatabaseService.create(player);
    }

    @GetMapping("/{playerId}")
    public Player getSinglePlayer(@PathVariable Long playerId){
        return playerDatabaseService.getOne(playerId);
    }

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerDatabaseService.getAll();
    }
}

