package com.example.gamerating.integration;

import com.example.gamerating.controller.GameController;
import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.GameConverter;
import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.vo.GameVO;
import com.example.gamerating.enums.GenreType;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.GameService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;

@WebMvcTest(GameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GameIntegrationTests extends CrudIntegrationTests<Game, GameVO> {

    @MockBean
    private GameService service;

    @MockBean
    private GameConverter converter;

    @Override
    protected CrudService<Game> getService() {
        return service;
    }

    @Override
    protected Converter<Game, GameVO> getConverter() {
        return converter;
    }

    @Override
    protected void initObjects() {
        LocalDate releaseDate1 = LocalDate.of(2022, Month.MARCH, 22);
        LocalDate releaseDate2 = LocalDate.of(2021, Month.SEPTEMBER, 13);
        voToCreate = """
                { "title": "Game Title", "genreType": "ACTION", "releaseDate": "2022-03-22" }
                """;
        entityToCreate = Game.builder().title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        createdEntity = Game.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        voCreated = GameVO.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        expected = GameVO.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        newExpected = GameVO.builder().id(2L).title("Game Title 2").genreType(GenreType.ACTION).releaseDate(releaseDate2).build();
        listEntity1 = Game.builder().id(1L).title("Game Title").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        listEntity2 = Game.builder().id(2L).title("Game Title 2").genreType(GenreType.ACTION).releaseDate(releaseDate2).build();
        emptyObject = new Game();
        filterToFound1 = Game.builder().title("game title").build();
        filterToFound2 = Game.builder().title("game title 2").build();
        voFilterToFound1 = GameVO.builder().title("game title").build();
        voFilterToFound2 = GameVO.builder().title("game title 2").build();
        filterToNotFound = Game.builder().title("not found").build();
        voFailsValidations = GameVO.builder().title("").build();
        voToUpdate = """
                { "title": "Game Title Updated", "description": "description updated", "genreType": "ACTION", "releaseDate": "2022-03-22" }
                """;
        toUpdate = Game.builder().title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        entityUpdated =  Game.builder().id(1L).title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        voUpdated = GameVO.builder().id(1L).title("Game Title Updated").description("description updated").genreType(GenreType.ACTION)
                .releaseDate(releaseDate1).build();
        voToPartialUpdate = GameVO.builder().title("Game Title Updated").build();
        entityToPartialUpdate = Game.builder().title("Game Title Updated").build();
        entityPartialUpdated = Game.builder().id(1L).title("Game Title Updated").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        voPartialUpdated = GameVO.builder().id(1L).title("Game Title Updated").genreType(GenreType.ACTION).releaseDate(releaseDate1).build();
        basePath = "/v1/game";
    }

}
