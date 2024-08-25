package com.example.accessingdatamysql.service;

import org.springframework.stereotype.service;
import com.example.accessingdatamysql.entity.*;
import com.example.accessingdatamysql.repository.*;
import java.io.IOException;
@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    public List<User> getAllUsers(){
        return repo.findAll();
    }
    public User getUserById(int id){
        return repo.findById(id).orElse(null);
    }
}