package com.flipp.seven.domain.player;

import com.flipp.seven.domain.card.Card;
import com.flipp.seven.domain.game.Game;
import com.flipp.seven.domain.player.power.HeroicPowerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int score;

    private boolean stopped;

    @ManyToOne
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "player_hand",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> hand = new ArrayList<>();

    private boolean isReady = false;

    private boolean isMaster = false;

    @Enumerated(EnumType.STRING)
    private HeroicPowerType heroicPowerType = HeroicPowerType.NONE;

    private boolean heroicPowerActive = false;

    private int order;
}
