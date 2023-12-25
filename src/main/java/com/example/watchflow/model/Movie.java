package com.example.watchflow.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie"
    )
    @SequenceGenerator(
            name = "movie",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    private Long id;
    private String title;
    private String image;
    private String content;
    private String genre;
    private String productionYear;
    private String director;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "movie_watchers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> watchers = new HashSet<>();




}
