package com.example.watchflow.repository;

import com.example.watchflow.model.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
}
