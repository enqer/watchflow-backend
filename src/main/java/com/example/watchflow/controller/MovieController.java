package com.example.watchflow.controller;

import com.example.watchflow.model.Movie;
import com.example.watchflow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService service;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movies = service.getMovies();
        if (movies.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie newMovie = service.addMovie(movie);
        if (newMovie != null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(newMovie);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
}
