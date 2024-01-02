package com.example.watchflow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedAt;
    @Column(columnDefinition = "text")
    private String content;
    private String image;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(
            name = "author_id"
    )
    private Author author;
}
