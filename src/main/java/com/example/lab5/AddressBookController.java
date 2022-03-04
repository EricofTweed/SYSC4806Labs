package com.example.lab5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressBookController {


    private final AddressBookRepository repo;


    @PostMapping("/addressbook")
    public AddressBook postAddressBook(){
    AddressBook addressBook = new AddressBook();
    return repo.save(addressBook);
    }

    @PostMapping("/addBuddy/{id}")
    public AddressBook postAddressBook(@PathVariable Long id, @RequestBody BuddyInfo buddy){
        AddressBook addressBook = repo.findById(id).orElse(null);
        if (addressBook != null) {
            addressBook.addBuddyInfo(buddy);
            repo.save(addressBook);
        }
        return addressBook;
    }

    @PostMapping(value = "/addressbook/{id}/buddyinfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook postBuddyInfo(@PathVariable Long id, @RequestBody BuddyInfo buddy){
        AddressBook addressbook = repo.findById(id).orElse(null);
        if (addressbook != null) {
            addressbook.addBuddyInfo(buddy);
            repo.save(addressbook);
        }
        return addressbook;
    }

    @DeleteMapping(value = "/addressbook/{id}/buddyinfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook deleteBuddyInfo(@PathVariable Long id, @RequestBody BuddyInfo buddy){
        AddressBook addressbook = repo.findById(id).orElse(null);
        if (addressbook != null) {
            addressbook.removeBuddyInfo(buddy);
            repo.save(addressbook);
        }
        return addressbook;
    }

    @GetMapping("/addressbook/{id}")
    public AddressBook getAddressBook(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/addressbook")
    public Iterable<AddressBook> getAllAddressBook(){
        return repo.findAll();
    }
}
