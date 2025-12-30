package com.flipp.seven.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RefreshStatusGameRequest {
    private Long idGame;
    private Long idPlayer;
}
