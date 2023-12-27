package com.example.watchflow.controller;

import com.example.watchflow.dto.AuthorDto;
import com.example.watchflow.model.Author;
import com.example.watchflow.service.AuthorService;
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
public class AuthorController {

    private final AuthorService service;


    @PostMapping("/author")
    public ResponseEntity<Author> addNews(@RequestBody AuthorDto author){
        Author newAuthor = service.addAuthor(author);
        if (newAuthor != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
        else
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
    }
}
