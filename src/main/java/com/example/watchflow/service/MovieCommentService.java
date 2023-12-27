package com.example.watchflow.service;


import com.example.watchflow.dto.MovieCommentRequestDto;
import com.example.watchflow.dto.MovieCommentResponseDto;
import com.example.watchflow.dto.mapper.MovieCommentDtoMapper;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.MovieComment;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.MovieCommentRepository;
import com.example.watchflow.repository.MovieRepository;
import com.example.watchflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommentService {

    private final MovieCommentRepository movieCommentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MovieCommentDtoMapper movieCommentDtoMapper;

    public MovieCommentResponseDto addCommentToMovie(MovieCommentRequestDto comment) {
        Optional<User> u = userRepository.findById(comment.userId());
        Optional<Movie> m = movieRepository.findById(comment.movieId());
        if (u.isPresent() && m.isPresent()){
            MovieComment movieComment =
                    MovieComment.builder()
                            .content(comment.content())
                            .publishedAt(LocalDate.now())
                            .movie(m.get())
                            .user(u.get())
                            .build();
            movieCommentRepository.save(movieComment);
            return movieCommentDtoMapper.apply(movieComment);
        }
        return null;
    }

    public List<MovieCommentResponseDto> getCommentsByMovieId(Long movieId){
        return movieCommentRepository
                .findAll()
                .stream()
                .filter(movieComment -> movieComment.getMovie().getId().equals(movieId))
                .map(movieCommentDtoMapper)
                .toList();
    }
}
