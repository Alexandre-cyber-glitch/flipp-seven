package com.flipp.seven.dto.heroicpower.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AskHeroicPowerRequest {
    private Long idGame;
    private Long idPlayer;
}
