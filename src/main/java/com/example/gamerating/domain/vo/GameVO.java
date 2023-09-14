package com.example.gamerating.domain.vo;

import com.example.gamerating.enums.GenreType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class GameVO implements AbstractEntityVO {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private GenreType genreType;

    @NotNull
    private LocalDate releaseDate;

}
