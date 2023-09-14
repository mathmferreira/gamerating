package com.example.gamerating.converter;

import com.example.gamerating.domain.model.AbstractEntity;
import com.example.gamerating.domain.vo.AbstractEntityVO;

public interface Converter<T extends AbstractEntity, VO extends AbstractEntityVO> {

    VO convertToVO(T entity);

    T convertToEntity(VO vo);

}
