package com.example.lab5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AddressBookView {

    private final AddressBookRepository repo;

    @GetMapping("/createAddressBook")
    public String initialPage(Model model){
        model.addAttribute("addressbook", new AddressBook());
        return "createAddressbook";
    }

    @PostMapping("/createAddressBook")
    public String makeAddressBook(Model model, @ModelAttribute AddressBook addressBook){
        repo.save(addressBook);
        model.addAttribute("id", addressBook.getId());
        model.addAttribute("buddies", addressBook.getBuddies());
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "addressbookfile";
    }

    @GetMapping("/addressbook/{id}")
    public String getAddressBook(@PathVariable Long id, Model model){
        AddressBook addressBook = repo.findById(id).orElse(null);
        if (addressBook != null) {
            model.addAttribute("id", id);
            model.addAttribute("buddies", addressBook.getBuddies());
            model.addAttribute("buddyInfo", new BuddyInfo());
        }
        return "addressbookfile";
    }

    @PostMapping("/addressbook/{id}")
    public String postAddressBook(@PathVariable Long id, Model model, @ModelAttribute BuddyInfo buddyInfo){
        AddressBook addressBook = repo.findById(id).orElse(null);
        if (addressBook != null) {
            addressBook.addBuddyInfo(buddyInfo);
            repo.save(addressBook);
            model.addAttribute("id", id);
            model.addAttribute("buddies", addressBook.getBuddies());
            model.addAttribute("buddyInfo", new BuddyInfo());
        }
        return "addressbookfile";
    }



}
