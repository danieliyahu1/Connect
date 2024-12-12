package com.connect.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersWrapper {
    private List<User> users;

    public UsersWrapper() {
        this.users = new ArrayList<>();
    }

    //****Seems meaningless if it only calls build****
    public void init ()
    {
        this.build();
    }

    private void build()
    {
        this.createUser("Eliyahu", "Stutaw", "1@gmail.com");
        this.createUser("Son", "Goku", "z@gmail.com");
    }

    private void createUser(String firstName, String lastName, String email)
    {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        this.addUser(user);
    }

    private void addUser(User user)
    {
        this.users.add(user);
    }
}
