package com.example.demo.service;

import com.example.demo.DB.DbMaria;
import com.example.demo.api.model.User;
import com.example.demo.common.Errors.ExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {


    @Autowired
    private DbMaria dbMaria;


    public UserService() {

    }


    public Optional<User> getUser(Integer id) {
        return dbMaria.findById(id);
    }


    public List<User> getUsers() {
        List<User> data;
        data = dbMaria.findAll();

        return data;
    }

    public User createUser(String name, String email, int age) {
       return dbMaria.save(new User(name, age, email));
    }

    public boolean deleteUser(Integer id) throws Exception {
        var isExist =  dbMaria.findById(id);
        if(isExist.isEmpty()) {
            throw new ExceptionClass("No se encontro", HttpStatus.NOT_FOUND);
        }
        try {
            dbMaria.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ExceptionClass("REVISAR LOGS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ;


    public  Optional<User> updateUser(int id, String name, String email, Integer age) {
        Optional<User> isExist =  dbMaria.findById(id);
        if(isExist.isEmpty()) return Optional.empty();
     User  userSelect  = isExist.get();
        if( name != null) {
            userSelect.setName(name);
           }
            if(age != null) {
                userSelect.setAge(age);
            }
            if( email != null) {
                userSelect.setEmail(email);
            }

        return Optional.of(dbMaria.save(userSelect));

    }
}