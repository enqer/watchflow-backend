package com.example.watchflow.service;


import com.example.watchflow.dto.NewsRequestDto;
import com.example.watchflow.dto.NewsResponseDto;
import com.example.watchflow.dto.mapper.NewsDtoMapper;
import com.example.watchflow.model.Author;
import com.example.watchflow.model.News;
import com.example.watchflow.repository.AuthorRepository;
import com.example.watchflow.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final NewsDtoMapper newsDtoMapper;

    public NewsResponseDto addNews(NewsRequestDto news) {
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
            return new NewsResponseDto(
                    newNews.getId(),
                    newNews.getTitle(),
                    newNews.getPublishedAt().toString(),
                    newNews.getContent(),
                    newNews.getImage(),
                    newNews.getAuthor().getId()
            );
        }
        return null;
    }

    public List<NewsResponseDto> getNews() {
        return newsRepository
                .findAll()
                .stream()
                .map(newsDtoMapper)
                .toList();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).get();
    }

    public List<NewsResponseDto> getLastestNews(int last) {
        List<NewsResponseDto> news = new ArrayList<>(newsRepository
                .findAll()
                .stream()
                .map(newsDtoMapper)
                .toList());
        Collections.reverse(news);
        return news.stream().limit(last).toList();
    }

    public void deleteNews(Long id) {
        News news = newsRepository.findById(id).get();
        newsRepository.delete(news);
    }
}
