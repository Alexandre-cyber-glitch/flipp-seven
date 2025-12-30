package com.flipp.seven.domain.game;

import com.flipp.seven.domain.card.Card;
import com.flipp.seven.domain.player.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private int currentRound;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_deck",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> deck = new ArrayList<>();

    @ManyToMany
    private List<Card> discardPile = new ArrayList<>();
}
