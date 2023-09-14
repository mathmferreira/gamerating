package com.example.gamerating.converter;

import com.example.gamerating.domain.model.User;
import com.example.gamerating.domain.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserVO> {

    @Override
    public UserVO convertToVO(User entity) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public User convertToEntity(UserVO vo) {
        User entity = new User();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }

}
