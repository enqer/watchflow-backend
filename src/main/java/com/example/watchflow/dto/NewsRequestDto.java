package com.example.watchflow.dto;

public record NewsRequestDto(
        String title,
        String content,
        String image,

        Long authorId

) {
}
