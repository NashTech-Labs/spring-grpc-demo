package com.nashtech.playerService.service.implementation;

import com.nashtech.grpc.PlayerRequest;
import com.nashtech.grpc.PlayerResponse;
import com.nashtech.grpc.PlayerServiceGrpc;
import com.nashtech.playerService.entity.Player;
import com.nashtech.playerService.repository.PlayerRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * gRPC Server Service implementation to return players from Player Service to the Team Service.
 * <p>
 * This class establishes a gRPC connection to the Player Service and
 * retrieves players belonging to a specific team using server streaming.
 * </p>
 *
 * @author [Nadra Ibrahim]
 */
@GrpcService
public class PlayerGrpcServiceImpl extends PlayerServiceGrpc.PlayerServiceImplBase {

    private final PlayerRepository playerRepository;

    public PlayerGrpcServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Retrieves all the players having the provided team id, from the database
     * and send it further to another service: teamService over grpc.
     *
     * @param request The gRPC request (the teamId in this case).
     * @param responseObserver The observer to send the response.
     *                         that is, the stream of data to be returned
     */
    @Override
    public void getPlayersOfTeam(PlayerRequest request,
                                 StreamObserver<PlayerResponse> responseObserver) {
        Long teamId = request.getTeamId();
        List<Player> players = playerRepository.findByTeamId(teamId);

        for(Player player : players){
            PlayerResponse playerResponse = PlayerResponse.newBuilder()
                .setPlayerId(player.getPlayerId())
                .setPlayerName(player.getPlayerName())
                .build();
            responseObserver.onNext(playerResponse); // Send response to client

            try {
                TimeUnit.SECONDS.sleep(1); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}