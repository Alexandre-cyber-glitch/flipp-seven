package com.flipp.seven.dto.lobby.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinGameResponse {
    private Long idGame;
    private Long idPlayer;
    private boolean status;
}
