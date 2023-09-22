package com.example.gamerating.service;

import com.example.gamerating.domain.model.User;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserService extends CrudService<User> {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Override
    protected EntityRepository<User> getRepository() {
        return repository;
    }

    @Override
    public User create(User entity) {
        if (repository.findByEmail(entity.getEmail()).isPresent()) {
            throw new EntityExistsException();
        }
        String pass = entity.getPass();
        entity.setPass(encoder.encode(pass));
        return super.create(entity);
    }

}
