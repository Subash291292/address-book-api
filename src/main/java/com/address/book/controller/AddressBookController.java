package com.address.book.controller;

import com.address.book.exception.InternalServerException;
import com.address.book.model.AddressBook;
import com.address.book.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressBookController {
    
    private Logger log = LoggerFactory.getLogger(AddressBookController.class);
    @Autowired
    private AddressBookService addressBookService;
    
    @PostMapping("/api/v1/address-book/{addressBookId}/contacts")
    public ResponseEntity<AddressBook> saveAddressBook(@PathVariable String addressBookId, @Valid @RequestBody AddressBook book) throws InternalServerException {
        try {
            log.info("saveAddressBook method invoked for this address : " + addressBookId);
            addressBookService.saveAddressBook(addressBookId, book);
            return new ResponseEntity<AddressBook>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception occurred in saveAddressBook method  : ", e);
            throw new InternalServerException("Error while saving the Address Book request");
        }
    }
    
    @GetMapping("/api/v1/address-book/contacts")
    public ResponseEntity<List<AddressBook>> findAddressBookList(@RequestParam(value = "unique", defaultValue = "false") final Boolean unique) throws InternalServerException {
        try {
            log.info("findFriendsList method invoked");
            List<AddressBook> friendsList = addressBookService.findAddressBookList(unique);
            return new ResponseEntity<>(friendsList, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Exception block  : ", e);
            throw new InternalServerException("Error while processing request");
        }
    }
}
