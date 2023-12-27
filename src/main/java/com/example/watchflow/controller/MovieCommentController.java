package com.example.watchflow.controller;

import com.example.watchflow.dto.MovieCommentRequestDto;
import com.example.watchflow.dto.MovieCommentResponseDto;
import com.example.watchflow.dto.NewsRequestDto;
import com.example.watchflow.dto.NewsResponseDto;
import com.example.watchflow.service.MovieCommentService;
import com.example.watchflow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieCommentController {

    private final MovieCommentService service;

    @PostMapping("/movie/comment")
    public ResponseEntity<MovieCommentResponseDto> addCommentToMovie(@RequestBody MovieCommentRequestDto comment){
        MovieCommentResponseDto newComment = service.addCommentToMovie(comment);
        if (newComment != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
        else
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
    }

    @DeleteMapping("/movies/comments/{id}")
    public ResponseEntity<?> deleteMovieComment(@PathVariable Long id){
        service.deleteMovieComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
