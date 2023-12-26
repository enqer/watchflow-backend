package com.example.watchflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating"
    )
    @SequenceGenerator(
            name = "rating",
            sequenceName = "rating_sequence",
            allocationSize = 1
    )
    private Long id;
    private Integer rate;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Movie movie;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private User user;
}
