package com.example.watchflow.controller;


import com.example.watchflow.dto.RatingDTO;
import com.example.watchflow.model.Rating;
import com.example.watchflow.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingService service;

    @PostMapping("/rating")
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO rating){
        RatingDTO rate = service.addRating(rating);
        if (rate != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(rate);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
}
