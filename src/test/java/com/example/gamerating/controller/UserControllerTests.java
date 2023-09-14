package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.UserConverter;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.UserVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests extends CrudControllerTests<User, UserVO> {

    @Mock
    private UserService service;

    @Spy
    private UserConverter converter;

    @InjectMocks
    private UserController controller;

    @Override
    protected CrudService<User> getService() {
        return service;
    }

    @Override
    protected Converter<User, UserVO> getConverter() {
        return converter;
    }

    @Override
    protected CrudController<User, UserVO> getController() {
        return controller;
    }

    @BeforeEach
    public void init() {
        voToCreate = UserVO.builder().name("Full Name").email("example@test.com").pass("pass123").build();
        createdEntity = User.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        voConverted = UserVO.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        expected = UserVO.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        secondEntity = User.builder().id(2L).name("Full Name 2").email("example2@test.com").pass("pass123").build();
        voSecondEntity = UserVO.builder().id(2L).name("Full Name 2").email("example2@test.com").pass("pass123").build();
        emptyFilter = new UserVO();
        voFilter = UserVO.builder().name("full name").build();
        voFilterNotFound = UserVO.builder().name("not found").build();
        entityToUpdate = User.builder().name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        voToUpdate = UserVO.builder().name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        entityUpdated = User.builder().id(1L).name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        expectedVoUpdated = UserVO.builder().id(1L).name("Full Name Updated").email("exampleupdated@test.com").pass("pass123").build();
        voToPartialUpdate = UserVO.builder().name("Full Name Updated").build();
        entityPartialUpdated = User.builder().id(1L).name("Full Name Updated").email("example@test.com").pass("pass123").build();
        voPartialUpdated =UserVO.builder().id(1L).name("Full Name Updated").email("example@test.com").pass("pass123").build();
    }

}
