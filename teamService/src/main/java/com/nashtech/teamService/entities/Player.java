package com.nashtech.teamService.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity class representing a Player.
 * <p>
 * This class is mapped to a database table using JPA annotations.
 * Each instance corresponds to a row in the "player" table.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String playerName;
    private Long teamId;
}

