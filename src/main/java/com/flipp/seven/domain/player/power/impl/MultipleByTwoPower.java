package com.flipp.seven.domain.player.power.impl;

import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.player.Player;
import com.flipp.seven.domain.player.power.HeroicPower;

public class MultipleByTwoPower implements HeroicPower {

    @Override
    public boolean canActivate(Game game, Player player) {
        return player.isHeroicPowerActive();
    }

    @Override
    public void activate(Game game, Player player) {
        //TODO
    }
}
