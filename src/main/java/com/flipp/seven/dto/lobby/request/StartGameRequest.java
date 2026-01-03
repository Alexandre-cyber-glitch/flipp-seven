package com.flipp.seven.dto.lobby.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StartGameRequest {
    private Long idGame;
    private Long idPlayer;
}
