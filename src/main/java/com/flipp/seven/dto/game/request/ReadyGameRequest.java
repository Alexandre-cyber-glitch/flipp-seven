package com.flipp.seven.dto.game.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReadyGameRequest {
    private Long idGame;
    private Long idPlayer;
    private String namePlayer;
}
