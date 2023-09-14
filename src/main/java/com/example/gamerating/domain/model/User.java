package com.example.gamerating.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.BeanUtils;

import static com.example.gamerating.util.DBConstUtils.*;

@Entity
@Table(name = "user" + TABLE)
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends AuditableEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user" + SEQUENCE)
    @SequenceGenerator(name = "user" + SEQUENCE, sequenceName = "user_id" + SEQUENCE, allocationSize = 1)
    @Column(name = "user" + ID, nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @JsonIgnore
    @ToString.Exclude
    @Column(nullable = false)
    private String pass;

    public User(User user) {
        BeanUtils.copyProperties(user, this);
    }

}
