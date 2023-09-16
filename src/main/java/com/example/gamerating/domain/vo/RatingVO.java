package com.example.gamerating.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingVO implements AbstractEntityVO {

    private Long id;

    @NotNull @Min(1) @Max(5)
    private Integer value;

    private String comments;

    @NotNull
    private Long userId;

    @NotNull
    private Long gameId;

}
