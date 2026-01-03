package com.flipp.seven.dto.heroicpower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SetHeroicPowerResponse {
    private Long idGame;
    private Long idPlayer;
    private HeroicPowerDto choice;
}
