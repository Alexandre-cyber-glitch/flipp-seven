package com.flipp.seven.controller;

import com.flipp.seven.dto.game.request.*;
import com.flipp.seven.dto.game.response.CreateGameResponse;
import com.flipp.seven.dto.game.response.JoinGameResponse;
import com.flipp.seven.dto.game.response.RefreshStatusGameResponse;
import com.flipp.seven.dto.game.response.StartGameResponse;
import com.flipp.seven.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController
{
    private GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<CreateGameResponse> create() {
        var resp = gameService.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinGameResponse> join(@RequestBody JoinGameRequest request) {
        var resp = gameService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/leave")
    public ResponseEntity<JoinGameResponse> leave(@RequestBody LeaveGameRequest request) {
        gameService.leave(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ready")
    public ResponseEntity<JoinGameResponse> ready(@RequestBody ReadyGameRequest request) {
        gameService.ready(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/start")
    public ResponseEntity<StartGameResponse> start(@RequestBody StartGameRequest request) {
        var resp = gameService.start(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshStatusGameResponse> refresh(@RequestBody RefreshStatusGameRequest request) {
        var resp = gameService.refreshStatusLobbyGame(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
    }
}
