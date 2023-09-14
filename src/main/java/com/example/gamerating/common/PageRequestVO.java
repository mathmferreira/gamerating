package com.example.gamerating.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageRequestVO implements Serializable {

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 20;

    @Builder.Default
    private Sort.Direction direction = Sort.Direction.ASC;

    @Builder.Default
    private String orderBy = "id";

}