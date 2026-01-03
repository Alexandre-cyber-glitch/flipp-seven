package com.flipp.seven.domain.player.power;

import lombok.Getter;


@Getter
public enum HeroicPowerType {
    DRAW_EXTRA_CARD("Frenetic Drawer", "Draw a card"),
    MAKE_IT_DOUBLE("King of Double", "Mutiply by 2 your hand"),
    STEAL("Stealer of card","Steal a card from a hand's player"),
    NONE("None", "No heroic power");

    private final String label;
    private final String description;

    HeroicPowerType(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
