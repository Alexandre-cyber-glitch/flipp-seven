package com.flipp.seven.domain.player.power;
import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.player.Player;

public interface HeroicPower {

    String getName();

    boolean canActivate(Game game, Player player);

    void activate(Game game, Player player);
}
