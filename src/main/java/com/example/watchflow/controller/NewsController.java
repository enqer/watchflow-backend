package com.example.watchflow.controller;

import com.example.watchflow.dto.MovieDTO;
import com.example.watchflow.dto.NewsRequestDto;
import com.example.watchflow.dto.NewsResponseDto;
import com.example.watchflow.model.News;
import com.example.watchflow.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;


    @GetMapping("/news")
    public ResponseEntity<List<NewsResponseDto>> getNews(){
        List<NewsResponseDto> news = service.getNews();
        if (news != null)
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNews(@PathVariable Long id){
        News news = service.getNewsById(id);
        if (news != null)
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/news/lastest")
    public ResponseEntity<List<NewsResponseDto>> getLastestNews(@RequestParam(required = false, defaultValue = "3") int last){
        List<NewsResponseDto> m = service.getLastestNews(last);
        if (m != null)
            return ResponseEntity.status(HttpStatus.OK).body(m);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/news")
    public ResponseEntity<NewsResponseDto> addNews(@RequestBody NewsRequestDto news){
        NewsResponseDto newNews = service.addNews(news);
        if (newNews != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newNews);
        else
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found news's author");
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id){
        service.deleteNews(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
