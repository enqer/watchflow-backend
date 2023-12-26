package com.example.watchflow.repository;

import com.example.watchflow.dto.RatingDTO;
import com.example.watchflow.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    RatingDTO findByMovieIdAndUserId(Long movieId, Long userId);
}
