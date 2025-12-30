package com.flipp.seven.service;

import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.game.GameStatus;
import com.flipp.seven.domain.player.Player;
import com.flipp.seven.dto.request.JoinGameRequest;
import com.flipp.seven.dto.request.ReadyGameRequest;
import com.flipp.seven.dto.request.StartGameRequest;
import com.flipp.seven.dto.response.CreateGameResponse;
import com.flipp.seven.dto.response.JoinGameResponse;
import com.flipp.seven.dto.response.StartGameResponse;
import com.flipp.seven.repository.CardRepository;
import com.flipp.seven.repository.GameRepository;
import com.flipp.seven.repository.PlayerRepository;
import lombok.AllArgsConstructor;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final CardRepository cardRepository;

    /**
     * Create the game by one player -> automatically the master
     * @return ID game et ID master
     */
    public CreateGameResponse create() {
        var game = new Game();
        var master = new Player();
        master.setMaster(true);
        master.setGame(game);

        // add the new player to the game's list
        game.getPlayers().add(master);
        game.setStatus(GameStatus.LOBBY);

        gameRepository.save(game);
        return new CreateGameResponse(game.getId(), game.getPlayers().get(0).getId());
    }

    /**
     * Join the game created by the master
     * @param request ID game
     * @return ID game and ID player
     */
    public JoinGameResponse join(@NonNull JoinGameRequest request) {
        var game = gameRepository.getReferenceById(request.getId());
        var newPlayer = new Player();
        newPlayer.setGame(game);
        game.getPlayers().add(newPlayer); // Hibernate automatically persists the new player because of cascade
        gameRepository.flush(); // forces Hibernate to generate IDs

        // No need to call gameRepository.save(game) if the game is managed and we're in a @Transactional method
        return new JoinGameResponse(game.getId(), newPlayer.getId(), true);
    }

    /**
     * Use to say ready to the server, needed before starting the game
     * @param request ID Game , ID player, name needed - selected by the player
     */
    public void ready(@NonNull ReadyGameRequest request) {
        var game = gameRepository.getReferenceById(request.getIdGame());

        // Hibernate automatically persists the player name and isReady because of cascade
        for (var player : game.getPlayers()) {
            if(player.getId().equals(request.getIdPlayer())) {
                player.setName(request.getNamePlayer());
                player.setReady(true);
            }
        }
    }

    /**
     * Start the game, need to be master and everybody ready. Set a timer to say to every front when start the game -> everybody synchronized
     * @param request ID Game , ID player
     * @return ID Game , ID player, boolean status, who is not ready, reason message
     */
    public StartGameResponse start(@NonNull StartGameRequest request) {
        var game = gameRepository.getReferenceById(request.getIdGame());
        var master = game.getPlayers().stream().filter(Player::isMaster).findFirst().orElse(null);

        if(master == null || !master.getId().equals(request.getIdPlayer())) {
            return new StartGameResponse(game.getId(), request.getIdPlayer(), false, List.of(), "You are not the master: " + request.getIdPlayer());
        }

        // Find players not ready
        List<Long> notReady = game.getPlayers().stream().filter(p -> !p.isReady()).map(Player::getId).toList();

        if(!notReady.isEmpty()) {
            return new StartGameResponse(game.getId(), request.getIdPlayer(), false, notReady,
                    "Some players are not ready");
        }

        // Everything ok, start the game
        game.setStatus(GameStatus.IN_PROGRESS);
        gameRepository.save(game); // persist the new status
        return new StartGameResponse(game.getId(), request.getIdPlayer(), true, List.of(), "Game started");
    }

}
