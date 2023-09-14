package com.example.gamerating.converter;

import com.example.gamerating.domain.model.Game;
import com.example.gamerating.domain.vo.GameVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GameConverter implements Converter<Game, GameVO> {

    @Override
    public GameVO convertToVO(Game entity) {
        GameVO vo = new GameVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public Game convertToEntity(GameVO vo) {
        Game entity = new Game();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }

}
