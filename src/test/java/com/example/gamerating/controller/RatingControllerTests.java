package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTests extends CrudControllerTests<Rating, RatingVO> {

    @Mock
    private RatingService service;

    @Spy
    private RatingConverter converter;

    @InjectMocks
    private RatingController controller;

    @Override
    protected CrudService<Rating> getService() {
        return service;
    }

    @Override
    protected Converter<Rating, RatingVO> getConverter() {
        return converter;
    }

    @Override
    protected CrudController<Rating, RatingVO> getController() {
        return controller;
    }

    @BeforeEach
    public void init() {
        voToCreate = RatingVO.builder().value(3).gameId(1L).userId(1L).build();
        createdEntity = Rating.builder().id(1L).value(3).build();
        voConverted = RatingVO.builder().id(1L).value(3).build();
        expected = RatingVO.builder().id(1L).value(3).build();
        secondEntity = Rating.builder().id(2L).value(4).build();
        voSecondEntity = RatingVO.builder().id(2L).value(4).build();
        emptyFilter = new RatingVO();
        voFilter = RatingVO.builder().value(3).build();
        voFilterNotFound = RatingVO.builder().value(1).build();
        entityToUpdate = Rating.builder().value(4).comments("Comments Updated").build();
        voToUpdate = RatingVO.builder().value(4).comments("Comments Updated").build();
        entityUpdated = Rating.builder().id(1L).value(4).comments("Comments Updated").build();
        expectedVoUpdated = RatingVO.builder().id(1L).value(4).comments("Comments Updated").build();
        voToPartialUpdate = RatingVO.builder().comments("Comments Updated").build();
        entityPartialUpdated = Rating.builder().id(1L).value(3).comments("Comments Updated").build();
        voPartialUpdated = RatingVO.builder().id(1L).value(3).comments("Comments Updated").build();
    }

}
