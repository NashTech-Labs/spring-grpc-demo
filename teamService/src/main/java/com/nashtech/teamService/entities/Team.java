package com.nashtech.teamService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity class representing a Team.
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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    private String teamName;
    transient private List<Player> players;
}