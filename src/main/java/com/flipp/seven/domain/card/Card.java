package com.flipp.seven.domain.card;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(name = "card_value") // Avoid reserved keyword
    private int value; // 0 to 12 if NUMBER
}
