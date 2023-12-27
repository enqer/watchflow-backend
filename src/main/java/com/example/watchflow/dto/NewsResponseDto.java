package com.example.watchflow.dto;

public record NewsResponseDto(
        Long id,
        String title,
        String publishedAt,
        String content,
        String image,
        Long authorId

) {
}
