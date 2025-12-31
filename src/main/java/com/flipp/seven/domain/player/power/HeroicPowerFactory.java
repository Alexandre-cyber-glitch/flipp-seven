package com.flipp.seven.domain.player.power;

import com.flipp.seven.domain.player.Player;
import com.flipp.seven.domain.player.power.impl.DrawExtraCardPower;
import com.flipp.seven.domain.player.power.impl.MultipleByTwoPower;

import java.util.EnumMap;
import java.util.Map;

public class HeroicPowerFactory {

    private static final Map<HeroicPowerType, HeroicPower> POWERS = new EnumMap<>(HeroicPowerType.class);

    static {
        POWERS.put(HeroicPowerType.DRAW_EXTRA_CARD, new DrawExtraCardPower());
        POWERS.put(HeroicPowerType.MAKE_IT_DOUBLE, new MultipleByTwoPower());
    }

    public static HeroicPower from(Player player) {
        return POWERS.get(player.getHeroicPowerType());
    }
}
