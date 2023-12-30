package com.example.watchflow.repository;

import com.example.watchflow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT u.id FROM Movie m JOIN m.watchers u WHERE m.id = ?1")
    List<Object[]> findMovieWatchersByMovieId(Long movieId);



    @Modifying
    @Query(value = "DELETE FROM movie_watchers WHERE movie_id = ?1 AND user_id = ?2", nativeQuery = true)
    void deleteMovieWatcherByUserId(Long movieId, Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM movie_watchers WHERE movie_id = ?1")
    void deleteWatchersByMovieId(Long id);
}
