package com.flipp.seven.domain.player.power.impl;

import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.player.Player;
import com.flipp.seven.domain.player.power.HeroicPower;

public class DrawExtraCardPower implements HeroicPower {

    @Override
    public String getName() {
        return "Frenetic Drawer";
    }

    @Override
    public boolean canActivate(Game game, Player player) {
        //TODO
        return false;
    }

    @Override
    public void activate(Game game, Player player) {
        //TODO
    }
}
