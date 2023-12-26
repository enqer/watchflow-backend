package com.example.watchflow.controller;


import com.example.watchflow.dto.RateDto;
import com.example.watchflow.dto.RatingDTO;
import com.example.watchflow.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingService service;

    @GetMapping("/ratings/movies/{movieId}/users/{userId}")
    public ResponseEntity<RatingDTO> getRatingOfMovieByUser(@PathVariable Long movieId, @PathVariable Long userId){
        RatingDTO ratingDTO = service.getRatingOfMovieByUser(movieId, userId);
        if (ratingDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(ratingDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/rating")
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO rating){
        RatingDTO rate = service.addRating(rating);
        if (rate != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(rate);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PatchMapping("/rating/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Long id, @RequestBody RateDto rate){
        service.updateRating(id, rate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
