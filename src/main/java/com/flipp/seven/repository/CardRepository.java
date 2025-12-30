package com.flipp.seven.repository;

import com.flipp.seven.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
