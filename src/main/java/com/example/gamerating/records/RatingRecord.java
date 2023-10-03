package com.example.gamerating.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RatingRecord(Long id, Integer value, String comments, Long userId, String email) {
}
