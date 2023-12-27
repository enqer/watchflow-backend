package com.example.watchflow.repository;

import com.example.watchflow.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository  extends JpaRepository<News, Long> {

}
