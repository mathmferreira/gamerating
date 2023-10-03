package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.records.RatingRecord;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/rating")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class RatingController extends CrudController<Rating, RatingVO> {

    private final RatingService service;
    private final RatingConverter converter;

    @Override
    protected CrudService<Rating> getService() {
        return service;
    }

    @Override
    protected Converter<Rating, RatingVO> getConverter() {
        return converter;
    }

    @GetMapping(value = "/game/{gameId}")
    public List<RatingRecord> findByGame(@PathVariable Long gameId) {
        List<Rating> list = service.findByGame(gameId);
        return list.parallelStream().map(it -> new RatingRecord(it.getId(), it.getValue(), it.getComments(), it.getUser().getId(), it.getUser().getEmail()))
                .toList();
    }

    @GetMapping(value = "/game/avg/{gameId}")
    public double findAvgByGame(@PathVariable Long gameId) {
        List<Rating> list = service.findByGame(gameId);
        double avg = list.parallelStream().mapToInt(Rating::getValue).average().orElse(0.0);
        return Double.parseDouble(new DecimalFormat("#.#").format(avg).replace(',', '.'));
    }

}
