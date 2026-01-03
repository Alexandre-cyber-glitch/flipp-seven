package com.flipp.seven.service;

import com.flipp.seven.domain.player.power.HeroicPowerFactory;
import com.flipp.seven.dto.heroicpower.request.AskHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.request.SetHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.response.AskHeroicPowerResponse;
import com.flipp.seven.dto.heroicpower.response.HeroicPowerChoice;
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
        var heroicList = HeroicPowerFactory.pick(3);
        var choices = heroicList.stream().map(
                type -> new HeroicPowerChoice(type.name(), type.getLabel(), type.getDescription())).toList();

        return new AskHeroicPowerResponse(request.getIdGame(), player.getId(), choices);
    }

    public SetHeroicPowerResponse setChoice(@NonNull SetHeroicPowerRequest request) {
        var player = manager.getPlayerByIdInGame(request.getIdGame(), request.getIdPlayer());
        var idHeroicPower = request.getIdHeroic();
        HeroicPowerFactory.set(player, idHeroicPower);

        var playerHeroicPowerType = player.getHeroicPowerType();
        var choice = new HeroicPowerChoice(playerHeroicPowerType.name(), playerHeroicPowerType.getLabel(), playerHeroicPowerType.getDescription());
        return new SetHeroicPowerResponse(request.getIdGame(), player.getId(), choice);
    }
}
