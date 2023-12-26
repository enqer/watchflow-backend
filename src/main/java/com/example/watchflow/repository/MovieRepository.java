package com.example.watchflow.repository;

import com.example.watchflow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT u.id FROM Movie m JOIN m.watchers u WHERE m.id = ?1")
    List<Object[]> findMovieWatchersByMovieId(Long movieId);

}
