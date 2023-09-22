package com.example.gamerating.repository;

import com.example.gamerating.domain.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends EntityRepository<Rating> {

    List<Rating> findByGameId(Long id);

}
