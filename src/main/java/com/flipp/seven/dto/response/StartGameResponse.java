package com.flipp.seven.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class StartGameResponse {
    private Long idGame;
    private Long idPlayer;

    private boolean status;
    private List<Long> idsNotReady;
    private String msg;
}
