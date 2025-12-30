package com.flipp.seven.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateGameResponse {
    private Long idGame;
    private Long idPlayer;
}
