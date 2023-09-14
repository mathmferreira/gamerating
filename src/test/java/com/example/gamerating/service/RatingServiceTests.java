package com.example.gamerating.service;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.enums.GenreType;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

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
    private final Game game = new Game(1L, "Game Title", null, GenreType.ACTION, LocalDate.of(2022, Month.MARCH, 22));

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

}
