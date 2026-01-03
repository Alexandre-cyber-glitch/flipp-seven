package com.flipp.seven.dto.game.structure;

import com.flipp.seven.dto.heroicpower.response.HeroicPowerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlayerStateDto {

    private String name;
    private HeroicPowerDto power;
    private int order;
}
