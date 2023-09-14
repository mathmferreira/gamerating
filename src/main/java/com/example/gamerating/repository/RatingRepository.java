package com.example.gamerating.repository;

import com.example.gamerating.domain.model.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends EntityRepository<Rating> {
}
