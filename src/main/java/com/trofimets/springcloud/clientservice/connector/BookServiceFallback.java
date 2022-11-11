package com.trofimets.springcloud.clientservice.connector;

import com.trofimets.springcloud.clientservice.model.Book;
import com.trofimets.springcloud.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BookServiceFallback implements BookServiceConnector {
    private ClientService clientService;
    private final Logger logger = Logger.getLogger(BookServiceFallback.class.getName());

    @Autowired
    public BookServiceFallback(@Lazy ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<Book> getAllBooks() {
        logger.log(Level.INFO, "Backup method called");
        return clientService.getAllBooksFallback();

    }
}
