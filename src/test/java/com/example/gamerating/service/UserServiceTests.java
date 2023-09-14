package com.example.gamerating.service;

import com.example.gamerating.domain.model.User;
import com.example.gamerating.repository.EntityRepository;
import com.example.gamerating.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests extends CrudServiceTests<User> {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Override
    protected EntityRepository<User> getRepository() {
        return repository;
    }

    @Override
    protected CrudService<User> getService() {
        return service;
    }

    @BeforeEach
    public void init() {
        entityToCreate = User.builder().name("Full Name").email("example@test.com").pass("pass123").build();
        expected = User.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        emptyObject = new User();
        filterToNotFound = User.builder().name("not found").build();
        filterEntity1 = User.builder().name("full name").build();
        filterEntity2 = User.builder().name("full name 2").build();
        toUpdate = User.builder().name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        entityUpdated = User.builder().id(1L).name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        toPartialUpdate = User.builder().name("Full Name Updated").build();
        partialUpdated = User.builder().id(1L).name("Full Name Updated").email("example@test.com").pass("pass123").build();
    }

}
