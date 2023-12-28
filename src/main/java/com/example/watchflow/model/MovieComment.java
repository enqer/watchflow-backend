package com.example.watchflow.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movie_comments")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class MovieComment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_comments"
    )
    @SequenceGenerator(
            name = "movie_comments",
            sequenceName = "movie_comments_sequence",
            allocationSize = 1
    )
    private Long id;
    private String content;
    private LocalDate publishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
