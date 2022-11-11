package com.trofimets.springcloud.clientservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trofimets.springcloud.clientservice.connector.BookServiceConnector;
import com.trofimets.springcloud.clientservice.connector.BookServiceUpdateConnector;
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
    private BookServiceUpdateConnector updateConnector;

    @Autowired
    public ClientService(BookServiceConnector connector, BookServiceUpdateConnector updateConnector) {
        this.connector = connector;
        this.updateConnector = updateConnector;
    }

    public List<Book> getAllBooks() {
        return connector.getAllBooks();
    }

    public List<Book> getAllBooksFallback() {
        return updateConnector.getAllBooks();
    }
}
