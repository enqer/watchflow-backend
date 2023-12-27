package com.example.watchflow.dto.mapper;

import com.example.watchflow.dto.MovieCommentResponseDto;
import com.example.watchflow.model.MovieComment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MovieCommentDtoMapper implements Function<MovieComment, MovieCommentResponseDto> {
    @Override
    public MovieCommentResponseDto apply(MovieComment movieComment) {
        return new MovieCommentResponseDto(
                movieComment.getId(),
                movieComment.getContent(),
                movieComment.getPublishedAt().toString(),
                movieComment.getMovie().getId(),
                movieComment.getUser().getId(),
                movieComment.getUser().getLogin()
        );
    }
}
