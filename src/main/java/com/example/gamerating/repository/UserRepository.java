package com.example.gamerating.repository;

import com.example.gamerating.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends EntityRepository<User> {
}
