package com.example.gamerating.integration;

import com.example.gamerating.controller.UserController;
import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.UserConverter;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.UserVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.UserService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserIntegrationTests extends CrudIntegrationTests<User, UserVO> {

    @MockBean
    private UserService service;

    @MockBean
    private UserConverter converter;

    @Override
    protected CrudService<User> getService() {
        return service;
    }

    @Override
    protected Converter<User, UserVO> getConverter() {
        return converter;
    }

    @Override
    protected void initObjects() {
        voToCreate = """
                { "name": "Full Name", "email": "example@test.com", "pass": "pass123" }
                """;
        entityToCreate = User.builder().name("Full Name").email("example@test.com").pass("pass123").build();
        createdEntity = User.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        voCreated = UserVO.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        expected = UserVO.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        newExpected = UserVO.builder().id(2L).name("Full Name 2").email("example2@test.com").pass("pass123").build();
        listEntity1 = User.builder().id(1L).name("Full Name").email("example@test.com").pass("pass123").build();
        listEntity2 = User.builder().id(2L).name("Full Name 2").email("example2@test.com").pass("pass123").build();
        emptyObject = new User();
        filterToFound1 = User.builder().name("full name").build();
        filterToFound2 = User.builder().name("full name 2").build();
        voFilterToFound1 = UserVO.builder().name("full name").build();
        voFilterToFound2 = UserVO.builder().name("full name 2").build();
        filterToNotFound = User.builder().name("not found").build();
        voFailsValidations = UserVO.builder().name("").email("").pass("pass123").build();
        voToUpdate = """
                { "name": "Full Name Updated", "email": "example.updated@test.com", "pass": "pass123" }
                """;
        toUpdate = User.builder().name("Full Name Updated").email("example.updated@test.com").pass("pass123").build();
        entityUpdated = User.builder().id(1L).name("Full Name Updated").email("example.updated@test.com").pass("pass123").build();
        voUpdated = UserVO.builder().id(1L).name("Full Name Updated").email("example.updated@test.com").pass("pass123").build();
        voToPartialUpdate = UserVO.builder().name("Full Name Updated").build();
        entityToPartialUpdate = User.builder().name("Full Name Updated").build();
        entityUpdated = User.builder().id(1L).name("Full Name Updated").email("example@test.com").pass("pass123").build();
        voPartialUpdated = UserVO.builder().id(1L).name("Full Name Updated").email("example@test.com").pass("pass123").build();
        basePath = "/v1/user";
    }

}
