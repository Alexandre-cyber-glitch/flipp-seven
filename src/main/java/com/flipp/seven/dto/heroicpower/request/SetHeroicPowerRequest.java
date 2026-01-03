package com.flipp.seven.dto.heroicpower.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SetHeroicPowerRequest {
    private Long idGame;
    private Long idPlayer;
    private String idHeroic;
}
