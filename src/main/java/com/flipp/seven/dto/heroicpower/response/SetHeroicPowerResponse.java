package com.flipp.seven.dto.heroicpower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SetHeroicPowerResponse {
    private Long idGame;
    private Long idPlayer;
    private HeroicPowerChoice choice;
}
