package com.flipp.seven.controller;

import com.flipp.seven.dto.heroicpower.request.AskHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.request.SetHeroicPowerRequest;
import com.flipp.seven.dto.heroicpower.response.AskHeroicPowerResponse;
import com.flipp.seven.dto.heroicpower.response.SetHeroicPowerResponse;
import com.flipp.seven.service.HeroicPowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroicpower")
@AllArgsConstructor
public class HeroicPowerController {

    private HeroicPowerService heroicPowerService;

    @PostMapping("/askchoice")
    public ResponseEntity<AskHeroicPowerResponse> askHeroicPowerChoice(@RequestBody AskHeroicPowerRequest request) {
        var resp = heroicPowerService.askChoice(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
    }

    @PostMapping("/setchoice")
    public ResponseEntity<SetHeroicPowerResponse> setHeroicPowerChoice(@RequestBody SetHeroicPowerRequest request) {
        var resp = heroicPowerService.setChoice(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
    }
}
