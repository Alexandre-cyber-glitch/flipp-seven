package com.flipp.seven.dto.heroicpower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AskHeroicPowerResponse {
    private Long idGame;
    private Long idPlayer;
    List<HeroicPowerDto> choices;
}
