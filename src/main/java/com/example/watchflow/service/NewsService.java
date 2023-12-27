package com.example.watchflow.service;


import com.example.watchflow.dto.NewsDto;
import com.example.watchflow.model.Author;
import com.example.watchflow.model.News;
import com.example.watchflow.repository.AuthorRepository;
import com.example.watchflow.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;

    public News addNews(NewsDto news) {
        Optional<Author> author = authorRepository.findById(news.authorId());
        if (author.isPresent()){
            News newNews =
                    News.builder()
                            .title(news.title())
                            .content(news.content())
                            .publishedAt(LocalDate.now())
                            .image(news.image())
                            .author(author.get())
                            .build();
            newsRepository.save(newNews);
            return newNews;
        }
        return null;
    }
}
