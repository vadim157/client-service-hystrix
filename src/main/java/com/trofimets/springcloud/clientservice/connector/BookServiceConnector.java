package com.trofimets.springcloud.clientservice.connector;

import com.trofimets.springcloud.clientservice.config.FeignConfig;
import com.trofimets.springcloud.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Primary
@FeignClient(name = "book-service",url = "http://localhost:8081",configuration = FeignConfig.class,fallback = BookServiceFallback.class)
public interface BookServiceConnector {

    @GetMapping("/show")
    List<Book> getAllBooks();
}
