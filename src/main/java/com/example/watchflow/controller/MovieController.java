package com.example.watchflow.controller;

import com.example.watchflow.dto.MovieDTO;
import com.example.watchflow.dto.SingleMovieDTO;
import com.example.watchflow.model.Movie;
import com.example.watchflow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService service;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies(){
        List<MovieDTO> movies = service.getMovies();
        if (movies.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<SingleMovieDTO> getMovieById(@PathVariable Long id){
        SingleMovieDTO m = service.getMovieById(id);
        if (m != null)
            return ResponseEntity.status(HttpStatus.OK).body(m);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/movies/{movieId}/watchers/{userId}")
    public ResponseEntity<?> isMovieWatcher(@PathVariable Long movieId, @PathVariable Long userId){
        boolean isWatcher = service.isMovieWatcher(movieId, userId);
        if (isWatcher)
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("isWatcher", true));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie newMovie = service.addMovie(movie);
        if (newMovie != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }

    @PostMapping("/movies/{movieId}/watchers/{userId}")
    public ResponseEntity<?> addWatcher(@PathVariable Long movieId, @PathVariable Long userId){
        service.addWatcher(movieId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/movies/{movieId}/watchers/{userId}")
    public ResponseEntity<?> deleteWatcher(@PathVariable Long movieId, @PathVariable Long userId){
        service.deleteWatcher(movieId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
