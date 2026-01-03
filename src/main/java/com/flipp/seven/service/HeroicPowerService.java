package com.flipp.seven.service;

import com.flipp.seven.domain.game.GameStatus;
import com.flipp.seven.domain.player.power.HeroicPowerFactory;
import com.flipp.seven.dto.heroicpower.request.AskHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.request.SetHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.response.AskHeroicPowerResponse;
import com.flipp.seven.dto.heroicpower.response.HeroicPowerDto;
import com.flipp.seven.dto.heroicpower.response.SetHeroicPowerResponse;
import com.flipp.seven.manager.GameManager;
import com.flipp.seven.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class HeroicPowerService {
    private final GameManager manager;
    private final GameRepository gameRepository;

    public AskHeroicPowerResponse askChoice(@NonNull AskHeroicPowerRequest request) {
        var player = manager.getPlayerByIdInGame(request.getIdGame(), request.getIdPlayer());
        player.setReady(false); // set false for this player
        var heroicList = HeroicPowerFactory.pick(3);
        var choices = heroicList.stream().map(
                type -> new HeroicPowerDto(type.name(), type.getLabel(), type.getDescription())).toList();

        return new AskHeroicPowerResponse(request.getIdGame(), player.getId(), choices);
    }

    public SetHeroicPowerResponse setChoice(@NonNull SetHeroicPowerRequest request) {
        var player = manager.getPlayerByIdInGame(request.getIdGame(), request.getIdPlayer());
        var game = gameRepository.getReferenceById(request.getIdGame());

        var idHeroicPower = request.getIdHeroic();
        HeroicPowerFactory.set(player, idHeroicPower);
        player.setReady(true); // heroic phase finish

        // if every player is ready -> GameStatus.RUNNING
        if(manager.allPlayersReadyForHeroicPower(game.getPlayers())){
            game.setStatus(GameStatus.START);
        }

        // DTO
        var playerHeroicPowerType = player.getHeroicPowerType();
        var choice = new HeroicPowerDto(playerHeroicPowerType.name(), playerHeroicPowerType.getLabel(), playerHeroicPowerType.getDescription());

        return new SetHeroicPowerResponse(request.getIdGame(), player.getId(), choice);
    }
}
