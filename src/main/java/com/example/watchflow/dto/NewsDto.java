package com.example.watchflow.dto;

import com.example.watchflow.model.Author;

import java.time.LocalDate;

public record NewsDto(
        String title,
        String content,
        String image,

        Long authorId

) {
}
