package com.dev.wackr.Repos;

import com.dev.wackr.Tables.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    
    public User getUserByEmail(String email);

}