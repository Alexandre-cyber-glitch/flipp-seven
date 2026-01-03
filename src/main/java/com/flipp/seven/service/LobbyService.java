package com.flipp.seven.service;

import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.game.GameStatus;
import com.flipp.seven.domain.player.Player;
import com.flipp.seven.dto.lobby.request.*;
import com.flipp.seven.dto.lobby.response.CreateGameResponse;
import com.flipp.seven.dto.lobby.response.JoinGameResponse;
import com.flipp.seven.dto.lobby.response.RefreshStatusGameResponse;
import com.flipp.seven.dto.lobby.response.StartGameResponse;
import com.flipp.seven.manager.GameManager;
import com.flipp.seven.repository.GameRepository;
import lombok.AllArgsConstructor;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LobbyService {
    private final GameRepository gameRepository;
    private final GameManager manager;

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
        // Hibernate automatically persists the player name and isReady because of cascade
        var player = manager.getPlayerByIdInGame(request.getIdGame(), request.getIdPlayer());
        player.setName(request.getNamePlayer());
        player.setReady(true);
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
        game.setStatus(GameStatus.HEROIC_PHASE);
        game.setStarted(true);
        game.setStartTime(LocalDateTime.now().plusSeconds(10)); // start in 10s
        gameRepository.save(game); // persist the new status and start date time

        return new StartGameResponse(game.getId(), request.getIdPlayer(), true, List.of(), "Game started");
    }

    public void leave(@NonNull LeaveGameRequest request) {
        var game = gameRepository.getReferenceById(request.getIdGame());
        var player = manager.getPlayerByIdInGame(request.getIdGame(), request.getIdPlayer());
        game.getPlayers().remove(player);
    }

    /**
     * Send this to client every seconds
     * @param request ask if started or not
     * @return json info
     */
    public RefreshStatusGameResponse refreshStatusLobbyGame(@NonNull RefreshStatusGameRequest request) {
        var game = gameRepository.getReferenceById(request.getIdGame());

        // Find players ready
        var playersReady = game.getPlayers().stream().filter(Player::isReady).map(Player::getName).toList();
        return new  RefreshStatusGameResponse(game.getId(), request.getIdPlayer(), playersReady, game.isStarted(), game.getStartTime());
    }
}
