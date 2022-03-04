package com.example.lab5;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    public Long id;
    @OneToMany(cascade = CascadeType.ALL)
    public List<BuddyInfo> buddies;

    public AddressBook(){
        this.buddies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long i){
        this.id = i;
    }

    public void addBuddyInfo(BuddyInfo buddy) {
        this.buddies.add(buddy);
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public BuddyInfo getBuddy(int index){
        return buddies.get(index);
    }
    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public void removeBuddyInfo(BuddyInfo buddy) {
        this.buddies.remove(buddy);

    }
}
