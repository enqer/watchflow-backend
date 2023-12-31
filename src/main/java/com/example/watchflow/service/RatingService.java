package com.example.watchflow.service;


import com.example.watchflow.dto.RateDto;
import com.example.watchflow.dto.RatingDTO;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.Rating;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.MovieRepository;
import com.example.watchflow.repository.RatingRepository;
import com.example.watchflow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public RatingDTO addRating(RatingDTO rating) {
        User u = userRepository.findById(rating.userId()).orElseThrow(EntityNotFoundException::new);
        Movie m = movieRepository.findById(rating.movieId()).orElseThrow(EntityNotFoundException::new);

        Rating newRating =
                Rating.builder()
                        .rate(rating.rate())
                        .user(u)
                        .movie(m)
                        .build();
        ratingRepository.save(newRating);
        return new RatingDTO(
                    newRating.getId(),
                    rating.rate(),
                    rating.movieId(),
                    rating.userId()
        );
    }

    public RatingDTO getRatingOfMovieByUser(Long movieId, Long userId) {
        return ratingRepository.findByMovieIdAndUserId(movieId, userId);
    }

    public void updateRating(Long id, RateDto rate) {
        Optional<Rating> r = ratingRepository.findById(id);
        if (r.isPresent()){
            Rating rating = r.get();
            rating.setRate(rate.rate());
            ratingRepository.save(rating);
        }
    }

    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id).get();
        ratingRepository.delete(rating);
    }
}
