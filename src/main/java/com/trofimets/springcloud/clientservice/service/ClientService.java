package com.trofimets.springcloud.clientservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trofimets.springcloud.clientservice.connector.BookServiceConnector;
import com.trofimets.springcloud.clientservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private BookServiceConnector connector;
    private RestTemplate restTemplate;
    private final Logger LOG = Logger.getLogger(ClientService.class.getName());

    @Autowired
    public ClientService(BookServiceConnector connector, RestTemplate restTemplate) {
        this.connector = connector;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "failed")
    public List<Book> getAllBooks() {
        return connector.getAllBooks();
    }

    public List<Book> getAllBooksRT() {
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange("http://localhost:8081/data",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        List<Book> books = responseEntity.getBody();
        return books;
    }

    public List<Book> failed() {
        String error = "Service is not available now. Please try again later";
        LOG.log(Level.INFO, error);
        List<Book> books = new ArrayList<>(Arrays.asList(new Book("null","null","null","null")));
        return books;
    }
}
