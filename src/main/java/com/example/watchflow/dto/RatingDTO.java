package com.example.watchflow.dto;

public record RatingDTO(
        Long id,
        Integer rate,
        Long movieId,
        Long userId
) {
}
