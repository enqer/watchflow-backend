package com.example.watchflow.dto;

public record SingleMovieDTO(
        Long id,
        String title,
        String image,
        String content,
        String genre,
        String productionYear,
        String director,
        Double rating,
        Integer numOfRatings
) {
}
