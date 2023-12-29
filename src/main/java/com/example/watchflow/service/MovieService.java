package com.example.watchflow.service;


import com.example.watchflow.dto.MovieDTO;
import com.example.watchflow.dto.MovieRankDto;
import com.example.watchflow.dto.MovieResponseDto;
import com.example.watchflow.dto.SingleMovieDTO;
import com.example.watchflow.dto.mapper.MovieDTOMapper;
import com.example.watchflow.dto.mapper.MovieRankDtoMapper;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.Rating;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.MovieRepository;
import com.example.watchflow.repository.UserRepository;
import com.example.watchflow.utils.Number;
import com.example.watchflow.utils.Text;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MovieService {


    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieDTOMapper movieDTOMapper;
    private final MovieCommentService movieCommentService;
    private final MovieRankDtoMapper movieRankDtoMapper;


    public List<MovieDTO> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieDTOMapper)
                .toList();
    }

    public Movie addMovie(MovieResponseDto movie) {
        var newMovie = Movie.builder()
                .title(movie.title())
                .genre(movie.genre())
                .content(movie.content())
                .image(movie.image())
                .director(movie.director())
                .productionYear(movie.productionYear())
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
                Number.round(
                movie.getRatings()
                        .stream()
                        .map(Rating::getRate)
                        .reduce(0, Integer::sum).doubleValue()/(movie.getRatings().isEmpty() ? 1 : movie.getRatings().size()),1),
                movie.getRatings().size(),
                movieCommentService.getCommentsByMovieId(movie.getId())
                );

    }

    public void addWatcher(Long movieId, Long userId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<User> user = userRepository.findById(userId);
        if (movie.isPresent() && user.isPresent()){
            Movie m = movie.get();
            m.addWatchers(user.get());
            movieRepository.save(m);
        }

    }

    public boolean isMovieWatcher(Long movieId, Long userId) {
        List<Object[]> movies = movieRepository.findMovieWatchersByMovieId(movieId);
        for (Object[] m : movies){
            if (m[0] == userId)
                return true;
        }
        return false;

    }

    public void deleteWatcher(Long movieId, Long userId) {
        movieRepository.deleteMovieWatcherByUserId(movieId, userId);
    }

    public List<MovieDTO> getMoviesByGenre(String genre) {
        return movieRepository
                .findAll()
                .stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .map(movieDTOMapper)
                .toList();
    }

    public List<MovieDTO> getMoviesByTitle(String title) {
        return movieRepository
                .findAll()
                .stream()
                .filter(movie -> Text.isSimilarTitle(movie.getTitle(), title))
                .map(movieDTOMapper)
                .toList();
    }



    public List<MovieRankDto> getMoviesRanking(int first) {
        return movieRepository
                .findAll()
                .stream()
                .map(movieRankDtoMapper)
                .sorted((m1, m2) -> m2.rating().compareTo(m1.rating()))
                .limit(first)
                .toList();
    }

    public List<MovieDTO> getLastestMovies(int last) {
        List<MovieDTO> movies = new ArrayList<>(movieRepository
                .findAll()
                .stream()
                .map(movieDTOMapper)
                .toList());
        Collections.reverse(movies);
        return movies.stream().limit(last).toList();
    }

    public List<MovieDTO> getPopularMovies(int last) {
        return movieRepository
                .findAll()
                .stream()
                .map(movieRankDtoMapper)
                .sorted((m1, m2) -> m2.numOfRatings().compareTo(m1.numOfRatings()))
                .limit(last)
                .map(m -> new MovieDTO(m.id(),m.title(),m.image(),m.genre(),m.rating()))
                .toList();
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).get();
        movieRepository.delete(movie);
    }
}
