package com.flipp.seven.service;

import com.flipp.seven.domain.game.GameStatus;
import com.flipp.seven.domain.player.power.HeroicPowerFactory;
import com.flipp.seven.dto.game.request.InitialStatusGameRequest;
import com.flipp.seven.dto.game.response.InitialStatusGameResponse;
import com.flipp.seven.dto.game.structure.PlayerStateDto;
import com.flipp.seven.dto.heroicpower.response.HeroicPowerDto;
import com.flipp.seven.manager.GameManager;
import com.flipp.seven.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameService {
    private final GameManager manager;
    private final GameRepository gameRepository;

    public InitialStatusGameResponse getInitialStatusGame(@NonNull InitialStatusGameRequest request) {
        var game = gameRepository.getReferenceById(request.getIdGame());
        var status = game.getStatus();

        if(!status.equals(GameStatus.START)){
            return new InitialStatusGameResponse(game.getId(), false, status, new ArrayList<>());
        }

        // Set order
        var players = game.getPlayers();
        manager.assignOrderToPlayers(players);
        game.setStatus(GameStatus.RUNNING);

        // DTO
        List<PlayerStateDto> playerStateDtoList = new ArrayList<>();
        for(var player : players) {
            var playerHeroicPowerType = player.getHeroicPowerType();
            var heroicPowerDto = new HeroicPowerDto(playerHeroicPowerType.name(), playerHeroicPowerType.getLabel(), playerHeroicPowerType.getDescription());
            playerStateDtoList.add(new PlayerStateDto(player.getName(),heroicPowerDto, player.getOrder()));
        }

        return new InitialStatusGameResponse(game.getId(), true, status, playerStateDtoList);
    }
}
