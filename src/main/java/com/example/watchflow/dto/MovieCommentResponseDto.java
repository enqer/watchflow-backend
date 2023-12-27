package com.example.watchflow.dto;

public record MovieCommentResponseDto (
        Long id,
        String content,
        String publishedAt,
        Long movieId,
        Long userId,
        String userLogin
){

}
