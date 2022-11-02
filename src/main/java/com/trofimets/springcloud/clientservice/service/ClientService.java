package com.trofimets.springcloud.clientservice.service;

import com.trofimets.springcloud.clientservice.connector.BookServiceConnector;
import com.trofimets.springcloud.clientservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ClientService {

    private BookServiceConnector connector;
    private RestTemplate restTemplate;

    @Autowired
    public ClientService(BookServiceConnector connector, RestTemplate restTemplate) {
        this.connector = connector;
        this.restTemplate = restTemplate;
    }

    public List<Book> getAllBooks() {
        return connector.getAllBooks();
    }
    public List<Book> getAllBooksRT(){
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange("http://localhost:8081/data",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        List<Book> books = responseEntity.getBody();
        return books;
    }
}
