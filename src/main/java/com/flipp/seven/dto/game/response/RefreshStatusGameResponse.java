package com.flipp.seven.dto.game.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class RefreshStatusGameResponse {
    private Long idGame;
    private Long idPlayer;

    private List<String> playerNameReady;
    private boolean isStarted;
    private LocalDateTime timeToStart;
}
