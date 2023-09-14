package com.example.gamerating.controller;

import com.example.gamerating.converter.Converter;
import com.example.gamerating.converter.UserConverter;
import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.UserVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserController extends CrudController<User, UserVO> {

    private final UserService service;
    private final UserConverter converter;

    @Override
    protected CrudService<User> getService() {
        return service;
    }

    @Override
    protected Converter<User, UserVO> getConverter() {
        return converter;
    }

}
