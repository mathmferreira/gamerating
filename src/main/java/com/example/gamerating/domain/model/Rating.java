package com.example.gamerating.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.example.gamerating.util.DBConstUtils.*;

@Entity
@Table(name = "rating" + TABLE)
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Rating extends AuditableEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating" + SEQUENCE)
    @SequenceGenerator(name = "rating" + SEQUENCE, sequenceName = "rating_id" + SEQUENCE, allocationSize = 1)
    @Column(name = "rating" + ID, nullable = false)
    private Long id;

    @NotNull @Min(1) @Max(5)
    private Integer value;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user" + ID, foreignKey = @ForeignKey(name = "fk_user_rating"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game" + ID, foreignKey = @ForeignKey(name = "fk_game_rating"))
    private Game game;

}
