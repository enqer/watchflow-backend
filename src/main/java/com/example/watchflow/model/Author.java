package com.example.watchflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author"
    )
    @SequenceGenerator(
            name = "author",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(
            mappedBy = "author"
    )
    private List<News> news = new ArrayList<>();


}
