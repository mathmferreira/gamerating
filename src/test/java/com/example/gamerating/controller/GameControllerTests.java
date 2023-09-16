package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.GameConverter;
import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.vo.GameVO;
import com.example.gamerating.enums.GenreType;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

@ExtendWith(MockitoExtension.class)
public class GameControllerTests extends CrudControllerTests<Game, GameVO> {

    @Mock
    private GameService service;

    @Spy
    private GameConverter converter;

    @InjectMocks
    private GameController controller;

    @Override
    protected CrudService<Game> getService() {
        return service;
    }

    @Override
    protected Converter<Game, GameVO> getConverter() {
        return converter;
    }

    @Override
    protected CrudController<Game, GameVO> getController() {
        return controller;
    }

    @BeforeEach
    public void init() {
        LocalDate releaseDate1 = LocalDate.of(2022, Month.MARCH, 22);
        LocalDate releaseDate2 = LocalDate.of(2021, Month.SEPTEMBER, 13);
        voToCreate = GameVO.builder().title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        createdEntity = Game.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        voConverted = GameVO.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        expected = GameVO.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        secondEntity = Game.builder().id(2L).title("Game Title 2").genreType(GenreType.ACTION).releaseDate(releaseDate2).build();
        voSecondEntity = GameVO.builder().id(2L).title("Game Title 2").genreType(GenreType.ACTION).releaseDate(releaseDate2).build();
        emptyFilter = new GameVO();
        voFilter = GameVO.builder().title("game title").build();
        voFilterNotFound = GameVO.builder().title("not found").build();
        entityToUpdate = Game.builder().title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        voToUpdate = GameVO.builder().title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        entityUpdated = Game.builder().id(1L).title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        expectedVoUpdated = GameVO.builder().id(1L).title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        voToPartialUpdate = GameVO.builder().title("Game Title Updated").build();
        entityPartialUpdated = Game.builder().id(1L).title("Game Title Updated").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        voPartialUpdated = GameVO.builder().id(1L).title("Game Title Updated").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
    }

}
