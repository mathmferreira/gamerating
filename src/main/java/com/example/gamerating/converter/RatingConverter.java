package com.example.gamerating.converter;

import com.example.gamerating.domain.model.Rating;
import com.example.gamerating.domain.vo.RatingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter implements Converter<Rating, RatingVO> {

    @Override
    public RatingVO convertToVO(Rating entity) {
        RatingVO vo = new RatingVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public Rating convertToEntity(RatingVO vo) {
        Rating entity = new Rating();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }

}
