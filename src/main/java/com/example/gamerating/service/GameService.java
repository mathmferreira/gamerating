package com.example.gamerating.service;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class GameService extends CrudService<Game> {

    private final GameRepository repository;

    @Override
    protected EntityRepository<Game> getRepository() {
        return repository;
    }

}
