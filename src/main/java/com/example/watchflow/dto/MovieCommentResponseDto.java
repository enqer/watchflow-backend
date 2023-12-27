package com.example.watchflow.dto;

public record MovieCommentResponseDto (
        Long id,
        String content,
        String publishedAd,
        Long movieId,
        Long userId
){

}
