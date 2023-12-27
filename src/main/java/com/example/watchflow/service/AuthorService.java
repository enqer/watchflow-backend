package com.example.watchflow.service;

import com.example.watchflow.dto.AuthorDto;
import com.example.watchflow.model.Author;
import com.example.watchflow.model.News;
import com.example.watchflow.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author addAuthor(AuthorDto author) {
        Author newAuthor =
                Author.builder()
                        .firstName(author.firstName())
                        .lastName(author.lastName())
                        .build();
        authorRepository.save(newAuthor);
        return newAuthor;
    }
}
