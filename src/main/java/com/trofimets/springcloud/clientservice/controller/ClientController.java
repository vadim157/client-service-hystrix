package com.trofimets.springcloud.clientservice.controller;

import com.trofimets.springcloud.clientservice.model.Book;
import com.trofimets.springcloud.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/allBooksByFeign")
    public List<Book> getAllBooksList(){
        return clientService.getAllBooks();
    }

}
