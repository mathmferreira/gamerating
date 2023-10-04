package com.example.gamerating.integration;

import com.example.gamerating.controller.RatingController;
import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.records.RatingRecord;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

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

    @Test
    public void givenGameId_whenFindByGame_thenReturnStatus200() throws JsonProcessingException {
        listEntity1.setUser(User.builder().id(1L).email("example1@gmail.com").build());
        listEntity2.setUser(User.builder().id(2L).email("example2@gmail.com").build());
        RatingRecord record1 = new RatingRecord(1L, 3, null, 1L, "example1@gmail.com");
        RatingRecord record2 = new RatingRecord(2L, 4, null, 2L, "example2@gmail.com");
        List<RatingRecord> records = List.of(record1, record2);
        List<Rating> ratings = List.of(listEntity1, listEntity2);

        when(service.findByGame(1L)).thenReturn(ratings);

        given().when().get(basePath + "/game/1")
                .then().log().ifValidationFails()
                .assertThat().status(HttpStatus.OK)
                .body(notNullValue(), not(emptyString()))
                .body(equalTo(mapper.writeValueAsString(records)));
    }

    @Test
    public void givenGameId_whenFindByGame_thenReturnEmptyListStatus200() throws JsonProcessingException {
        List<RatingRecord> records = new ArrayList<>();

        given().when().get(basePath + "/game/0")
                .then().log().ifValidationFails()
                .assertThat().status(HttpStatus.OK)
                .body(notNullValue(), not(emptyString()))
                .body(equalTo(mapper.writeValueAsString(records)));
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnAverageRatingsValue() throws JsonProcessingException {
        double avgExpected = 3.5;

        when(service.findAvgByGame(1L)).thenReturn(avgExpected);

        given().when().get(basePath + "/game/avg/1")
                .then().log().ifValidationFails()
                .assertThat().status(HttpStatus.OK)
                .body(notNullValue(), not(emptyString()))
                .body(equalTo(mapper.writeValueAsString(avgExpected)));
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnZero() throws JsonProcessingException {
        double avgExpected = 0.0;

        when(service.findAvgByGame(0L)).thenReturn(avgExpected);

        given().when().get(basePath + "/game/avg/0")
                .then().log().ifValidationFails()
                .assertThat().status(HttpStatus.OK)
                .body(notNullValue(), not(emptyString()))
                .body(equalTo(mapper.writeValueAsString(avgExpected)));
    }

}
