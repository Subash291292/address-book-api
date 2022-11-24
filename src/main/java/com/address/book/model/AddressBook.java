package com.address.book.model;

import javax.validation.constraints.*;

public class AddressBook {
    
    private String id;
    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 1, max = 250)
    private String name;
    
    @NotEmpty(message = "User's mobile number cannot be empty.")
    @Pattern(regexp = "(\\+61|0)[0-9]{9}", message = "User's mobile number is invalid")
    private String phoneNumber;
    
    public AddressBook(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}
