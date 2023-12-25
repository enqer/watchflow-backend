package com.example.watchflow.service;


import com.example.watchflow.dto.MovieDTO;
import com.example.watchflow.dto.SingleMovieDTO;
import com.example.watchflow.dto.mapper.MovieDTOMapper;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.Rating;
import com.example.watchflow.repository.MovieRepository;
import com.example.watchflow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieDTOMapper movieDTOMapper;

    public List<MovieDTO> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieDTOMapper)
                .toList();
    }

    public Movie addMovie(Movie movie) {
        var newMovie = Movie.builder()
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .content(movie.getContent())
                .image(movie.getImage())
                .director(movie.getDirector())
                .productionYear(movie.getProductionYear())
                .build();

        return movieRepository.save(newMovie);
    }

    public SingleMovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new SingleMovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getImage(),
                movie.getContent(),
                movie.getGenre(),
                movie.getProductionYear(),
                movie.getDirector(),
                movie.getRatings()
                        .stream()
                        .map(Rating::getRate)
                        .reduce(0, Integer::sum).doubleValue()/(movie.getRatings().isEmpty() ? 1 : movie.getRatings().size())
                );

    }
}
