package com.flipp.seven.controller;

import com.flipp.seven.dto.game.request.InitialStatusGameRequest;
import com.flipp.seven.dto.game.response.InitialStatusGameResponse;
import com.flipp.seven.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController
{
    private GameService gameService;

    @PostMapping("/getinitialstate")
    public ResponseEntity<InitialStatusGameResponse> getInitialState(@RequestBody InitialStatusGameRequest request) {
        var resp = gameService.getInitialStatusGame(request);
        return ResponseEntity.ok(resp);
    }
}
