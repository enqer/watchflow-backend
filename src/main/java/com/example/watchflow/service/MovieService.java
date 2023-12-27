package com.example.watchflow.service;


import com.example.watchflow.dto.MovieDTO;
import com.example.watchflow.dto.SingleMovieDTO;
import com.example.watchflow.dto.mapper.MovieDTOMapper;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.Rating;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.MovieRepository;
import com.example.watchflow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
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
                        .reduce(0, Integer::sum).doubleValue()/(movie.getRatings().isEmpty() ? 1 : movie.getRatings().size()),
                movie.getRatings().size()
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
                .filter(movie -> isSimilarTitle(movie.getTitle(), title))
                .map(movieDTOMapper)
                .toList();
    }

    private boolean isSimilarTitle(String title, String searchedTitle) {
        double probability = 0.3;
        int lengthLongestSubtitle = calcLengthLongestSubtitle(title.toLowerCase(), searchedTitle.toLowerCase());
        double similarity = (double) lengthLongestSubtitle / Math.max(title.length(), searchedTitle.length());
        return similarity >= probability;

    }
    private int calcLengthLongestSubtitle(String title, String searchedTitle) {
        int[][] similarity = new int[title.length() + 1][searchedTitle.length() + 1];

        for (int i = 0; i <= title.length(); i++) {
            for (int j = 0; j <= searchedTitle.length(); j++) {
                if (i == 0 || j == 0) {
                    similarity[i][j] = 0;
                } else if (title.charAt(i - 1) == searchedTitle.charAt(j - 1)) {
                    similarity[i][j] = similarity[i - 1][j - 1] + 1;
                } else {
                    similarity[i][j] = Math.max(similarity[i - 1][j], similarity[i][j - 1]);
                }
            }
        }
        return similarity[title.length()][searchedTitle.length()];
        }
    }
