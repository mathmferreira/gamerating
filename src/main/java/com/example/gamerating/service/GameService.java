package com.example.gamerating.service;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class GameService extends CrudService<Game> {

    private final GameRepository repository;

    @Override
    protected EntityRepository<Game> getRepository() {
        return repository;
    }

    public List<Game> findTopRated(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "avgRating");
        Page<Game> page = repository.findAll(pageable);
        return page.getContent();
    }

}
