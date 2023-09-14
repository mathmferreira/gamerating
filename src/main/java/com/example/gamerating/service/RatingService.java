package com.example.gamerating.service;

import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class RatingService extends CrudService<Rating> {

    private final RatingRepository repository;

    @Override
    protected EntityRepository<Rating> getRepository() {
        return repository;
    }

}
