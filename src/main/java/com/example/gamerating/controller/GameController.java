package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.GameConverter;
import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.vo.GameVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/game")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class GameController extends CrudController<Game, GameVO> {

    private final GameService service;
    private final GameConverter converter;

    @Override
    protected CrudService<Game> getService() {
        return service;
    }

    @Override
    protected Converter<Game, GameVO> getConverter() {
        return converter;
    }

    @GetMapping(value = "/top/{size}")
    public List<GameVO> findTopRated(@PathVariable Integer size) {
        List<Game> list = service.findTopRated(size);
        return list.parallelStream().map(converter::convertToVO).sorted(Comparator.comparingDouble(GameVO::getAvgRating).reversed()).toList();
    }

}
