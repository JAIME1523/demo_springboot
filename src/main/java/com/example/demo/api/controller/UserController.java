package com.example.demo.api.controller;

import com.example.demo.api.model.User;
import com.example.demo.common.Errors.ErrorsHttp;
import com.example.demo.common.Errors.ExceptionClass;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public  ResponseEntity<Object> getUser(@RequestParam Integer id){
        var user =   userService.getUser(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorsHttp("Usuario no encontrado", 404));
        }
        return ok(user);


    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @PostMapping ("/user")
    public  ResponseEntity<Object> create(@RequestParam String name, @RequestParam String email, @RequestParam Integer age){


        try {
            return ok(userService.createUser(name, email, age));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorsHttp(e.getMessage(), 500));
        }
    }
    @DeleteMapping ("/user")
    public  ResponseEntity<Object> delete(@RequestParam Integer id){

        try {
            return ok(userService.deleteUser(id));
        }   catch (ExceptionClass e) {
            return ResponseEntity.status(e.getHttpStatus()).body(new ErrorsHttp(e.getMessage(), e.getStatus()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorsHttp(e.getMessage(), 500));
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) Integer age) {
        Optional<User> existingUser = userService.updateUser(id, name, email, age);
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorsHttp("Usuario no encontrado", 404));
        }

      return   ok(existingUser);
    }
}