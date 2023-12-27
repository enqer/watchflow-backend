package com.example.watchflow.dto;

public record MovieCommentRequestDto(
        String content,
        Long movieId,
        Long userId
) {
}
