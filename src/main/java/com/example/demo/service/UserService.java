package com.example.demo.service;

import com.example.demo.api.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Ida", 32, "ida@mail.com");
        User user2 = new User(2, "Hans", 26, "hans@mail.com");
        User user3 = new User(3, "Lars", 45, "lars@mail.com");
        User user4 = new User(4, "Ben", 32, "ben@mail.com");
        User user5 = new User(5, "Eva", 59, "eva@mail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public User getUser(Integer id) {
        User userget = null;
        for (User user : userList) {
            if (user.getId() == id) {
                userget = user;
                return userget;
            }
        }
        ;
        return userget;
    }


    public List<User> getUsers() {
        return userList;
    }

    public User createUser(String name, String email, int age) {
        int index = userList.size() - 1;
        int last = index > 0 ? userList.get(userList.size() - 1).getId() + 1 : 1;
        User user = new User(last, name, age, email);
        userList.add(user);
        return user;
    }

    public boolean deleteUser(Integer id) {
        System.out.println("El último elemento es: " + id);
        User user = getUser(id);

        if (user == null) {
            throw new NoSuchElementException("User not found");
        } else {
            userList.remove(user);

            return true;
        }

    }

    ;


    public User updateUser(int id, String name, String email, int age) {

        try {
            User userSelect = (User) userList.stream()
                    .filter(u -> u.getId() == id).findFirst()
                    .orElse(null);;
                    if(userSelect == null) return  null;
           if( name != null) {
               userSelect.setName(name);
           }
            if(age != 0) {
                userSelect.setAge(age);
            }
            if( email != null) {
                userSelect.setEmail(email);
            }

            return userSelect;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}