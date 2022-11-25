package com.address.book.service;

import com.address.book.model.AddressBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * I have added a few test cases for demonstration
 * more test cases are possible
 */

public class AddressBookServiceTest {
    
    private static final String TEST_ADDRESS_BOOK_MEL = "AddressBookMelbourne";
    private static final String TEST_ADDRESS_BOOK_SYD = "AddressBookSydney";
    
    private AddressBookService addressBookService;
    
    @Before
    public void init() {
        addressBookService = new AddressBookService();
    }
    
    @Test
    public void testSaveAddressBook() {
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_MEL, getNewTestAddressBook());
        AddressBook addressBook = getNewTestAddressBook();
        Assert.assertTrue(addressBook.getName().equals(addressBook.getName()));
    }
    
    @Test
    public void testAllUniqueAddressBooks() {
        
        List<AddressBook> retrievedAddressBooksBeforeAddingDuplicates = addressBookService.getFriendsList(true);
        
        //add contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_MEL, getNewTestAddressBook());
        
        //same as contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_MEL, getNewTestAddressBook());
        
        //contact2
        AddressBook addressBook = getNewTestAddressBook();
        addressBook.setName("Marc");
        
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_SYD, addressBook);
        
        //same as Contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_SYD, getNewTestAddressBook());
        
        //At this point we added 4 Address but the below returned list should contain addition of 2 new records
        List<AddressBook> retrievedAddressBookAfterAddingDuplicates = addressBookService.getFriendsList(true);
        
        Assert.assertTrue(2 == retrievedAddressBookAfterAddingDuplicates.size() - retrievedAddressBooksBeforeAddingDuplicates.size());
    }
    
    @Test
    public void testAllAddressBooks() {
        
        List<AddressBook> retrievedAddressBooksBeforeAddingDuplicates = addressBookService.getFriendsList(false);
        
        //add contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_MEL, getNewTestAddressBook());
        
        //same as contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_MEL, getNewTestAddressBook());
        
        //contact2
        AddressBook addressBook = getNewTestAddressBook();
        addressBook.setName("Marc");
        
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_SYD, addressBook);
        
        //same as Contact1
        addressBookService.saveAddressBook(TEST_ADDRESS_BOOK_SYD, getNewTestAddressBook());
        
        //At this point we added 4 Address but the below returned list should contain addition of 2 new records
        List<AddressBook> retrievedAddressBooksAfterAddingDuplicates = addressBookService.getFriendsList(false);
        
        Assert.assertTrue(4 == retrievedAddressBooksAfterAddingDuplicates.size() - retrievedAddressBooksBeforeAddingDuplicates.size());
    }
    
    private AddressBook getNewTestAddressBook() {
        return new AddressBook("Tom", "+61421435534");
    }
    
}
