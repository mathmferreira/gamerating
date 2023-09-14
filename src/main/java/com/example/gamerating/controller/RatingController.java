package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
