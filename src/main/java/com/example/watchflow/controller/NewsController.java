package com.example.watchflow.controller;

import com.example.watchflow.dto.NewsDto;
import com.example.watchflow.model.News;
import com.example.watchflow.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    @PostMapping("/news")
    public ResponseEntity<News> addNews(@RequestBody NewsDto news){
        News newNews = service.addNews(news);
        if (newNews != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newNews);
        else
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found news's author");
    }
}
