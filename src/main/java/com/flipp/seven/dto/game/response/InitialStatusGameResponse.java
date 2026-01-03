package com.flipp.seven.dto.game.response;

import com.flipp.seven.domain.game.GameStatus;
import com.flipp.seven.dto.game.structure.PlayerStateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class InitialStatusGameResponse {
    private Long idGame;
    private boolean start;
    private GameStatus status;
    List<PlayerStateDto> players;
}
