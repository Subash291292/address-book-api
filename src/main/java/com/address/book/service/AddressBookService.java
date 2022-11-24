package com.address.book.service;

import com.address.book.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
    private static final String ADDRESS_BOOK_MELBOURNE = "address-book-melbourne";
    private static final String ADDRESS_BOOK_SYDNEY = "address-book-sydney";
    
    //TODO: should be replaced with appropriate persistence
    private final Map<String, List<AddressBook>> addressBooks = new HashMap<>();
    
    public AddressBookService() {
        addressBooks.put(ADDRESS_BOOK_MELBOURNE, new LinkedList<>());
        addressBooks.put(ADDRESS_BOOK_SYDNEY, new LinkedList<>());

        this.saveAddressBook(ADDRESS_BOOK_MELBOURNE, new AddressBook("Thomas", "+61421435543"));
        this.saveAddressBook(ADDRESS_BOOK_MELBOURNE, new AddressBook("Victor", "+61421435543"));

        this.saveAddressBook(ADDRESS_BOOK_SYDNEY, new AddressBook("Steven", "+61435000111"));
        this.saveAddressBook(ADDRESS_BOOK_SYDNEY, new AddressBook("Victor", "+61421445543"));
    }
    
    public void saveAddressBook(final String addressBookId, final AddressBook book) {
        book.setId(UUID.randomUUID().toString());
        if (!addressBooks.containsKey(addressBookId)) {
            addressBooks.put(addressBookId, new ArrayList<>());
        }
        addressBooks.get(addressBookId).add(book);
    }
    public List<AddressBook> findAddressBookList(final Boolean unique) {
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBooks.values().forEach(addressBookList::addAll);
        List<AddressBook> sortedUsers = null;
        List<AddressBook> uniqueList = new ArrayList<>();
        if (unique) {
            final Map<String, String> addressBookMap = new HashMap();
            sortedUsers = addressBookList.stream()
                    .sorted(Comparator.comparing(AddressBook::getName))
                    .collect(Collectors.toList());
            sortedUsers.forEach(
                    addressBook -> {
                        if (null != addressBook.getName() && !addressBookMap.containsKey(addressBook.getName())) {
                            addressBookMap.put(addressBook.getName(), addressBook.getPhoneNumber());
                            uniqueList.add(addressBook);
                        }
                    }
            );
            return uniqueList;
        } else {
            sortedUsers = addressBookList.stream()
                    .sorted(Comparator.comparing(AddressBook::getName))
                    .collect(Collectors.toList());
        }
        return sortedUsers;
    }
}
