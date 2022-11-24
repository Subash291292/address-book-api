package com.address.book.controller;

import com.address.book.AddressBookApiApplication;
import com.address.book.model.AddressBook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * I have added a few test cases for demonstration
 * More test cases could be added to make the code more robust
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookControllerTest {
    private static final String ADDRES_BOOK_END_POINT = "/api/v1/address-book/address-book1/contacts";
    
    @LocalServerPort
    private int port;
    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
    
    @Test
    public void testRetrieveAllUniqueAddressBook() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/address-book/contacts?unique=true"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }
    
    @Test
    public void validateInvalidPhoneName() {
        AddressBook AddressBook = new AddressBook("subash", "481303516");
        HttpEntity<AddressBook> entity = new HttpEntity<>(AddressBook);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(ADDRES_BOOK_END_POINT),
                HttpMethod.POST, entity, String.class);
        Assert.assertTrue(response.getStatusCode().value() == 400);
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
}
