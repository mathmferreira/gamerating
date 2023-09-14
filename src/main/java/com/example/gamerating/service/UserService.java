package com.example.gamerating.service;

import com.example.gamerating.domain.model.User;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserService extends CrudService<User> {

    private final UserRepository repository;

    @Override
    protected EntityRepository<User> getRepository() {
        return repository;
    }

}
