package com.example.address_book_app.controller;

import com.example.address_book_app.model.Contact;
import com.example.address_book_app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public List<Contact> getAllContacts() {
        return service.getAllContacts();
    }
    @PostMapping("/add")
    public Contact addContact(@RequestBody Contact contact) {
        return service.saveContact(contact);
    }

}
