package com.nashtech.teamService.service;

import com.nashtech.grpc.PlayerRequest;
import com.nashtech.grpc.PlayerResponse;
import com.nashtech.grpc.PlayerServiceGrpc;
import com.nashtech.teamService.entities.Player;
import com.nashtech.teamService.service.implementation.TeamGrpcClientService;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamGrpcClientServiceUnitTest {
    @InjectMocks
    TeamGrpcClientService teamGrpcClientService;
    @Mock
    private PlayerServiceGrpc.PlayerServiceBlockingStub mockBlockingStub;


    @Test
    void testPrivateMethod_getPlayersOfTeam() throws StatusRuntimeException {
        // Inject the mock using reflection
        ReflectionTestUtils.setField(teamGrpcClientService, "serviceBlockingStub", mockBlockingStub);
        // Arrange
        PlayerRequest request = PlayerRequest.newBuilder().setTeamId(1L).build();
        PlayerResponse player1 = PlayerResponse.newBuilder()
                .setPlayerId(1).setPlayerName("Tony Stark").build();
        PlayerResponse player2 = PlayerResponse.newBuilder()
                .setPlayerId(1).setPlayerName("Steve Rogers").build();
        Iterator<PlayerResponse> mockIterator = List.of(player1, player2).iterator();
        when(mockBlockingStub.getPlayersOfTeam(request)).thenReturn(mockIterator);
        // Act
        List<Player> players = teamGrpcClientService.getPlayersOfTeam(1L);
        // Assert
        assertEquals(2, players.size());
        assertEquals("Tony Stark", players.get(0).getPlayerName());
        assertEquals("Steve Rogers", players.get(1).getPlayerName());
        verify(mockBlockingStub, times(1)).getPlayersOfTeam(request);
    }
}
