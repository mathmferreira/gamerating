package com.example.gamerating.repository;

import com.example.gamerating.domain.model.Rating;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends EntityRepository<Rating> {

    @EntityGraph(attributePaths = { "user" })
    List<Rating> findByGameId(Long id);

}
