package com.flipp.seven.controller;

import com.flipp.seven.dto.lobby.request.*;
import com.flipp.seven.dto.lobby.response.CreateGameResponse;
import com.flipp.seven.dto.lobby.response.JoinGameResponse;
import com.flipp.seven.dto.lobby.response.RefreshStatusGameResponse;
import com.flipp.seven.dto.lobby.response.StartGameResponse;
import com.flipp.seven.service.LobbyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lobby")
@AllArgsConstructor
public class LobbyController
{
    private LobbyService lobbyService;

    @PostMapping("/create")
    public ResponseEntity<CreateGameResponse> create() {
        var resp = lobbyService.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinGameResponse> join(@RequestBody JoinGameRequest request) {
        var resp = lobbyService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/leave")
    public ResponseEntity<JoinGameResponse> leave(@RequestBody LeaveGameRequest request) {
        lobbyService.leave(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ready")
    public ResponseEntity<JoinGameResponse> ready(@RequestBody ReadyGameRequest request) {
        lobbyService.ready(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/start")
    public ResponseEntity<StartGameResponse> start(@RequestBody StartGameRequest request) {
        var resp = lobbyService.start(request);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshStatusGameResponse> refresh(@RequestBody RefreshStatusGameRequest request) {
        var resp = lobbyService.refreshStatusLobbyGame(request);
        return ResponseEntity.ok(resp);
    }
}
