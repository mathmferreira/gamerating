package com.example.gamerating.repository;

import com.example.gamerating.domain.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends EntityRepository<Game> {
}
