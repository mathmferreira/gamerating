package com.example.gamerating.integration;

import com.example.gamerating.controller.RatingController;
import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(RatingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RatingIntegrationTests extends CrudIntegrationTests<Rating, RatingVO> {

    @MockBean
    private RatingService service;

    @MockBean
    private RatingConverter converter;

    @Override
    protected CrudService<Rating> getService() {
        return service;
    }

    @Override
    protected Converter<Rating, RatingVO> getConverter() {
        return converter;
    }

    @Override
    protected void initObjects() {
        voToCreate = """
                { "value": 3, "userId": 1, "gameId": 1 }
                """;
        entityToCreate = Rating.builder().value(3).build();
        createdEntity = Rating.builder().id(1L).value(3).build();
        voCreated = RatingVO.builder().id(1L).value(3).build();
        expected = RatingVO.builder().id(1L).value(3).build();
        newExpected = RatingVO.builder().id(2L).value(4).build();
        listEntity1 = Rating.builder().id(1L).value(3).build();
        listEntity2 = Rating.builder().id(2L).value(4).build();
        emptyObject = new Rating();
        filterToFound1 = Rating.builder().value(3).build();
        filterToFound2 = Rating.builder().value(4).build();
        voFilterToFound1 = RatingVO.builder().value(3).build();
        voFilterToFound2 = RatingVO.builder().value(4).build();
        filterToNotFound = Rating.builder().value(1).build();
        voFailsValidations = RatingVO.builder().value(3).build();;
        voToUpdate = """
                { "value": 4, "comments": "Comments Updated", "userId": 1, "gameId": 1 }
                """;
        toUpdate = Rating.builder().value(4).comments("Comments Updated").build();
        entityUpdated = Rating.builder().id(1L).value(4).comments("Comments Updated").build();
        voUpdated = RatingVO.builder().id(1L).value(4).comments("Comments Updated").build();
        voToPartialUpdate = RatingVO.builder().comments("Comments Updated").build();
        entityToPartialUpdate = Rating.builder().comments("Comments Updated").build();
        entityUpdated = Rating.builder().id(1L).value(3).comments("Comments Updated").build();
        voPartialUpdated = RatingVO.builder().id(1L).value(3).comments("Comments Updated").build();
        basePath = "/v1/rating";
    }

}
