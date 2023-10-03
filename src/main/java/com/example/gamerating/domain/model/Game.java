package com.example.gamerating.domain.model;

import com.example.gamerating.enums.GenreType;
import com.example.gamerating.util.QueryUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.Set;

import static com.example.gamerating.util.DBConstUtils.*;

@Entity
@Table(name = "game" + TABLE)
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Game extends AuditableEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game" + SEQUENCE)
    @SequenceGenerator(name = "game" + SEQUENCE, sequenceName = "game_id" + SEQUENCE, allocationSize = 1)
    @Column(name = "game" + ID, nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenreType genreType;

    @NotNull
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "game")
    private Set<Rating> ratings;

    @Formula(QueryUtils.GAME_AVG_RATING_FORMULA_QUERY)
    private Double avgRating;

}
