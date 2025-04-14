package com.nashtech.playerService.controller;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreatePlayer() throws Exception {
        String json = "{ \"playerName\": \"Tony Stark\" , \"teamId\": \"1\" }";

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(1))
                .andExpect(jsonPath("$.playerName").value("Tony Stark"))
                .andExpect(jsonPath("$.teamId").value(1));
    }

    @Test
    @Order(2)
    public void testGetSinglePlayer() throws Exception {
        // First create
        String json = "{ \"playerName\": \"Hulk\" , \"teamId\": \"1\" }";
        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        // Then fetch
        mockMvc.perform(get("/players/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(2))
                .andExpect(jsonPath("$.playerName").value("Hulk"))
                .andExpect(jsonPath("$.teamId").value(1));
    }

    @Test
    @Order(3)
    public void testGetAllPlayers() throws Exception {
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }
}