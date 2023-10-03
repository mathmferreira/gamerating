package com.example.gamerating.domain.vo;

import com.example.gamerating.enums.GenreType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameVO implements AbstractEntityVO {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private GenreType genreType;

    @NotNull
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate releaseDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double avgRating;

}
