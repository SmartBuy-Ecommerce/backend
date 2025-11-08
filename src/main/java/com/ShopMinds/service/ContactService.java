package com.ShopMinds.service;

import com.ShopMinds.model.Contact;
import com.ShopMinds.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;
    public Contact contactus(Contact contact) {
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setSubject(contact.getSubject());
        newContact.setMessage(contact.getMessage());

        emailService.contactusEmail(contact.getName(), contact.getEmail(), contact.getMessage());
        return contactRepository.save(newContact);
    }
}
