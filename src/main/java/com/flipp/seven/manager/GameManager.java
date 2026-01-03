package com.flipp.seven.manager;

import com.flipp.seven.domain.player.Player;
import com.flipp.seven.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameManager {
    private final GameRepository gameRepository;

    public Player getPlayerByIdInGame(Long gameId, Long playerId) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found: " + gameId));

        return game.getPlayers().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerId));
    }
}
