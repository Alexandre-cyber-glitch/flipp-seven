package com.flipp.seven.repository;

import com.flipp.seven.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {


}
