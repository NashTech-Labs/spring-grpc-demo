package com.nashtech.playerService.service;

import com.nashtech.grpc.PlayerRequest;
import com.nashtech.grpc.PlayerResponse;
import com.nashtech.playerService.entity.Player;
import com.nashtech.playerService.repository.PlayerRepository;
import com.nashtech.playerService.service.implementation.PlayerGrpcServerServiceImpl;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerGrpcServiceUnitTest {

    @Mock
    PlayerRepository playerRepository;
    @InjectMocks
    PlayerGrpcServerServiceImpl playerGrpcServerServiceImpl;

    @Test
    void getPlayersOfTeamShouldReturnListOfPlayersOfTheTeam() throws Exception {
        // Arrange
        Long teamId= 1L;
        PlayerRequest request = PlayerRequest.newBuilder().setTeamId(teamId).build();
        StreamRecorder<PlayerResponse> responseObserver = StreamRecorder.create();
        List<Player> players = List.of(new Player(1L, "Iron Man", teamId), new Player(2L, "Hulk", teamId));
        when(playerRepository.findByTeamId(teamId)).thenReturn(players);
        // Act
        playerGrpcServerServiceImpl.getPlayersOfTeam(request, responseObserver);
        // Assert
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<PlayerResponse> playerResponses = responseObserver.getValues();
        assertEquals(2, playerResponses.size());
        assertEquals(players.get(0).getPlayerId(), playerResponses.get(0).getPlayerId());
        assertEquals(players.get(0).getPlayerName(), playerResponses.get(0).getPlayerName());
        assertEquals(players.get(0).getTeamId(),  playerResponses.get(0).getTeamId());
    }

}