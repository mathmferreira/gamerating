package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.RatingConverter;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.RatingVO;
import com.example.gamerating.records.RatingRecord;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        secondEntity = Rating.builder().id(2L).value(5).build();
        voSecondEntity = RatingVO.builder().id(2L).value(5).build();
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

    @Test
    public void givenGameId_whenFindByGame_thenReturnListRatings() {
        createdEntity.setUser(User.builder().id(1L).email("example1@gmail.com").build());
        secondEntity.setUser(User.builder().id(2L).email("example2@gmail.com").build());
        List<Rating> ratings = List.of(createdEntity, secondEntity);

        when(service.findByGame(1L)).thenReturn(ratings);

        RatingRecord record1 = new RatingRecord(1L, 3, null, 1L, "example1@gmail.com");
        RatingRecord record2 = new RatingRecord(2L, 5, null, 2L, "example2@gmail.com");
        List<RatingRecord> records = List.of(record1, record2);

        List<RatingRecord> actual = assertDoesNotThrow(() -> controller.findByGame(1L));
        assertFalse(actual.isEmpty());
        assertEquals(records.toString(), actual.toString());
    }

    @Test
    public void givenGameId_whenFindByGame_thenReturnEmptyList() {
        when(service.findByGame(0L)).thenReturn(Collections.emptyList());

        List<RatingRecord> actual = assertDoesNotThrow(() -> controller.findByGame(0L));
        assertTrue(actual.isEmpty());
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnAverageRatingsValue() {
        double avgExpected = 4.0;
        when(service.findAvgByGame(1L)).thenReturn(avgExpected);

        double actual = assertDoesNotThrow(() -> controller.findAvgByGame(1L));
        assertEquals(avgExpected, actual);
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnZero() {
        double avgExpected = 0.0;
        when(service.findAvgByGame(0L)).thenReturn(avgExpected);

        double actual = assertDoesNotThrow(() -> controller.findAvgByGame(0L));
        assertEquals(avgExpected, actual);
    }

}
