package com.example.address_book_app.service;

import com.example.address_book_app.model.Contact;
import com.example.address_book_app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return repository.findById(id);
    }

    public Contact saveContact(Contact contact) {
        return repository.save(contact);
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
