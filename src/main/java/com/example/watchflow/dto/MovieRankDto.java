package com.example.watchflow.dto;

public record MovieRankDto(
        Long id,
        String title,
        String genre,
        String director,
        String image,
        Double rating,
        Integer numOfRatings
) {
}
