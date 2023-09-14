package com.example.gamerating.service;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.enums.GenreType;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests extends CrudServiceTests<Game> {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameService service;

    @Override
    protected EntityRepository<Game> getRepository() {
        return repository;
    }

    @Override
    protected CrudService<Game> getService() {
        return service;
    }

    @BeforeEach
    public void init() {
        entityToCreate = Game.builder().title("Game Title").genreType(GenreType.ACTION)
                .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();
        expected = Game.builder().id(1L).title("Game Title").genreType(GenreType.ACTION)
                .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();
        emptyObject = new Game();
        filterToNotFound = Game.builder().title("not found").build();
        filterEntity1 = Game.builder().title("game title").build();
        filterEntity2 = Game.builder().title("game title 2").build();
        toUpdate = Game.builder().title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();
        entityUpdated = Game.builder().id(1L).title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();
        toPartialUpdate = Game.builder().title("Game Title Updated").build();
        partialUpdated = Game.builder().id(1L).title("Game Title Updated").genreType(GenreType.ACTION)
                .releaseDate(LocalDate.of(2022, Month.MARCH, 22)).build();
    }

}
