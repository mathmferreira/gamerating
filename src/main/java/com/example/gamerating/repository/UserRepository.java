package com.example.gamerating.repository;

import com.example.gamerating.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends EntityRepository<User> {

    Optional<User> findByEmail(String email);

}
