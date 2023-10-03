package com.example.gamerating.service;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.enums.GenreType;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTests extends CrudServiceTests<Rating> {

    @Mock
    private RatingRepository repository;

    @InjectMocks
    private RatingService service;

    @Override
    protected EntityRepository<Rating> getRepository() {
        return repository;
    }

    @Override
    protected CrudService<Rating> getService() {
        return service;
    }

    private final User user = new User(1L, "Full Name", "example@test.com", "pass123");
    private final Game game = Game.builder().id(1L).title("Game Title").description(null).genreType(GenreType.ACTION)
            .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();

    @BeforeEach
    public void init() {
        entityToCreate = Rating.builder().value(3).user(user).game(game).build();
        expected = Rating.builder().id(1L).value(3).user(user).game(game).build();
        emptyObject = new Rating();
        filterToNotFound = Rating.builder().value(1).build();
        filterEntity1 = Rating.builder().value(3).build();
        filterEntity2 = Rating.builder().value(4).build();
        toUpdate = Rating.builder().value(5).comments("Comments Updated").user(user).game(game).build();
        entityUpdated = Rating.builder().id(1L).value(5).comments("Comments Updated").user(user).game(game).build();
        toPartialUpdate = Rating.builder().value(4).build();
        partialUpdated = Rating.builder().id(1L).value(5).user(user).game(game).build();
    }

    @Test
    public void givenGameId_whenFindByGame_thenReturnListRatings() {
        entityUpdated.setId(2L);
        List<Rating> ratings = List.of(expected, entityUpdated);
        when(repository.findByGameId(1L)).thenReturn(ratings);

        List<Rating> actual = assertDoesNotThrow(() -> service.findByGame(1L));
        assertFalse(actual.isEmpty());
        assertEquals(ratings.toString(), actual.toString());
    }

    @Test
    public void givenGameId_whenFindByGame_thenReturnEmptyList() {
        when(repository.findByGameId(0L)).thenReturn(Collections.emptyList());

        List<Rating> actual = assertDoesNotThrow(() -> service.findByGame(0L));
        assertTrue(actual.isEmpty());
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnAverageRatingsValue() {
        entityUpdated.setId(2L);
        List<Rating> ratings = List.of(expected, entityUpdated);
        when(repository.findByGameId(1L)).thenReturn(ratings);

        double actual = assertDoesNotThrow(() -> service.findAvgByGame(1L));
        assertEquals(4, actual);
    }

    @Test
    public void givenGameId_whenFindAvgByGame_thenReturnZero() {
        when(repository.findByGameId(0L)).thenReturn(Collections.emptyList());

        double actual = assertDoesNotThrow(() -> service.findAvgByGame(0L));
        assertEquals(0, actual);
    }

}
