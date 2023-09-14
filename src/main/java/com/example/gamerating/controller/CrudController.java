package com.example.gamerating.controller;

import com.example.gamerating.common.PageRequestVO;
import com.example.gamerating.common.UpdaterExample;
import com.example.gamerating.converter.Converter;
import com.example.gamerating.domain.model.AbstractEntity;
import com.example.gamerating.domain.vo.AbstractEntityVO;
import com.example.gamerating.service.CrudService;
import com.example.gamerating.util.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<T extends AbstractEntity, VO extends AbstractEntityVO> {

    protected abstract CrudService<T> getService();

    protected abstract Converter<T, VO> getConverter();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VO create(@Valid @RequestBody VO vo) {
        T entity = getConverter().convertToEntity(vo);
        T created = getService().create(entity);
        return getConverter().convertToVO(created);
    }

    @GetMapping(value = "/{id}")
    public VO findById(@PathVariable Long id) {
        T entity = getService().findById(id).orElseThrow(EntityNotFoundException::new);
        return getConverter().convertToVO(entity);
    }

    @GetMapping
    public ResponseEntity<List<VO>> findAll(@ModelAttribute VO filters, @ModelAttribute PageRequestVO pageable) {
        T entityFilters = getConverter().convertToEntity(filters);
        Page<T> result = getService().findAll(entityFilters, getPageableOf(pageable));
        HttpHeaders headers = new HttpHeaders();
        headers.add("currentPage", String.valueOf(pageable.getPage() + 1));
        headers.add("totalElements", String.valueOf(result.getTotalElements()));
        headers.add("totalPages", String.valueOf(result.getTotalPages()));
        List<VO> content = result.map(getConverter()::convertToVO).getContent();
        return new ResponseEntity<>(content, headers, HttpStatus.PARTIAL_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public VO update(@PathVariable Long id, @Valid @RequestBody VO vo) {
        T toUpdate = getConverter().convertToEntity(vo);
        T updated = getService().update(id, toUpdate);
        return getConverter().convertToVO(updated);
    }

    @PatchMapping(value = "/{id}")
    public VO partialUpdate(@PathVariable Long id, @RequestBody VO vo) {
        T toUpdate = getConverter().convertToEntity(vo);
        T updated = getService().update(id, new UpdaterExample<>(toUpdate, ValidationUtils.getNullPropertyNames(toUpdate)));
        return getConverter().convertToVO(updated);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        getService().deleteById(id);
    }

    protected Pageable getPageableOf(PageRequestVO vo) {
        return PageRequest.of(vo.getPage(), vo.getSize(), vo.getDirection(), vo.getOrderBy());
    }

}
