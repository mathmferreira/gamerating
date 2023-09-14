package com.example.gamerating.common;

import com.example.gamerating.domain.model.AbstractEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UpdaterExample<T extends AbstractEntity> implements Example<T> {

    private final T probe;
    private final String[] ignoredProperties;

    @Override
    public T getProbe() {
        return probe;
    }

    @Override
    public ExampleMatcher getMatcher() {
        List<String> list = new ArrayList<>(List.of(ignoredProperties));
        list.add("id");
        return ExampleMatcher.matchingAll().withIgnorePaths(list.toArray(new String[0]));
    }

    public String[] getIgnoredPaths() {
        return getMatcher().getIgnoredPaths().toArray(new String[0]);
    }

}
