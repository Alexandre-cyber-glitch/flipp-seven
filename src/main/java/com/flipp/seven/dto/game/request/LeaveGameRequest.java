package com.flipp.seven.dto.game.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LeaveGameRequest {
    private Long idGame;
    private Long idPlayer;
}
