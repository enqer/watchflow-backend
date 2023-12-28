package com.example.watchflow.dto.mapper;

import com.example.watchflow.dto.MovieRankDto;
import com.example.watchflow.model.Movie;
import com.example.watchflow.model.Rating;
import com.example.watchflow.utils.Number;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MovieRankDtoMapper implements Function<Movie, MovieRankDto> {
    @Override
    public MovieRankDto apply(Movie movie) {
        return new MovieRankDto(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getDirector(),
                movie.getImage(),
                Number.round(movie.getRatings()
                        .stream()
                        .map(Rating::getRate)
                        .reduce(0, Integer::sum).doubleValue()/(movie.getRatings().isEmpty() ? 1 : movie.getRatings().size()),1),
                movie.getRatings().size()
        );
    }
}
