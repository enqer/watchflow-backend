package com.example.watchflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class News {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "news"
    )
    @SequenceGenerator(
            name = "news",
            sequenceName = "news_sequence",
            allocationSize = 1
    )
    private Long id;
    private String title;
    private LocalDate publishedAt;
    @Column(columnDefinition = "text")
    private String content;
    private String image;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "author_id"
    )
    private Author author;
}
