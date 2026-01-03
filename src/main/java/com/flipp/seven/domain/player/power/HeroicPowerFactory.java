package com.flipp.seven.domain.player.power;

import com.flipp.seven.domain.player.Player;
import com.flipp.seven.domain.player.power.impl.DrawExtraCardPower;
import com.flipp.seven.domain.player.power.impl.MultipleByTwoPower;
import com.flipp.seven.domain.player.power.impl.StealPower;

import java.util.*;

public class HeroicPowerFactory {

    private static final Map<HeroicPowerType, HeroicPower> POWERS = new EnumMap<>(HeroicPowerType.class);

    static {
        POWERS.put(HeroicPowerType.DRAW_EXTRA_CARD, new DrawExtraCardPower());
        POWERS.put(HeroicPowerType.MAKE_IT_DOUBLE, new MultipleByTwoPower());
        POWERS.put(HeroicPowerType.STEAL, new StealPower());
    }

    public static HeroicPower from(Player player) {
        return POWERS.get(player.getHeroicPowerType());
    }

    /**
     * Pick a random count of HeroicPowerType
     * @param count desired pick possible
     * @return HeroicPowerType
     */
    public static List<HeroicPowerType> pick(int count) {
        List<HeroicPowerType> allTypes = new ArrayList<>(POWERS.keySet());
        Collections.shuffle(allTypes);
        return allTypes.stream().limit(count).toList();
    }

    /**
     * Set a HeroicPowerType chosen by the client
     * @param player player / user
     * @param idPower String id
     */
    public static void set(Player player, String idPower) {
        HeroicPowerType powerType;
        try {
            powerType = HeroicPowerType.valueOf(idPower);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unknown heroic power: " + idPower);
        }
        player.setHeroicPowerType(powerType);
        player.setHeroicPowerActive(true);
    }
}
