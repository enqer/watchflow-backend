package com.example.watchflow.dto;

public record MovieDTO(
        Long id,
        String title,
        String image,
        String genre,
        Double rating
) {

}
