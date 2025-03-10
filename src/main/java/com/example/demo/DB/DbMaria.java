package com.example.demo.DB;

import com.example.demo.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DbMaria extends JpaRepository<User, Integer> {
}
