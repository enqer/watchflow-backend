package com.example.watchflow.dto.mapper;

import com.example.watchflow.dto.NewsResponseDto;
import com.example.watchflow.model.News;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NewsDtoMapper implements Function<News, NewsResponseDto> {
    @Override
    public NewsResponseDto apply(News news) {
        return new NewsResponseDto(
                news.getId(),
                news.getTitle(),
                news.getPublishedAt().toString(),
                news.getContent(),
                news.getImage(),
                news.getAuthor().getId()
        );
    }
}
