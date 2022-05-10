package com.dev.wackr.Controllers;

import com.dev.wackr.Config.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.lang.SecurityException;

import com.dev.wackr.Repos.*;
import com.dev.wackr.Tables.*;

@RestController
@RequestMapping(path = "/wackr/api/user")
public class UserAPI {

    private static final Logger logger = LoggerFactory.getLogger(UserAPI.class);

    @Autowired
    private UserRepo userRep;

    @Autowired
    public CompanyRepo comRepo;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(
        path = "/new",
        consumes = "*/*")
    public ModelAndView addUser ( User user ){

        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("User");

        if (user == null) {
            throw new SecurityException("Could not create user.");
        }
        userRep.save(user);
        return new ModelAndView("redirect:/index.html");  
    }


    @GetMapping("/get")
    public @ResponseBody Iterable<User> findAllUsers(){
        return userRep.findAll();
    }

    @DeleteMapping("/delete")
    public @ResponseBody List<User> deleteUser(@RequestParam long uid){
        userRep.deleteById(uid);
        return userRep.findAll();
    }     
    
    @GetMapping("/get/{uniqueUserId}")
    public @ResponseBody User findUserById(@PathVariable long uniqueUserId){

        Optional<User> resp = userRep.findById(uniqueUserId);

        if (!resp.isPresent()){
            throw new SecurityException("User not found!");
        }

        return resp.get();
    }
    
    @GetMapping("/get/User")
    public @ResponseBody User findUserByEmail(@RequestBody User User){

       User resp = userRep.getUserByEmail(User.getEmail()); 	

        return resp;		
    
    }

      // -----------   Exception handlers, Spring boot will call these if any of the above methods return an exception specified in the "ExceptionHandler" annotation

      @ExceptionHandler(SecurityException.class)
      @ResponseStatus(HttpStatus.UNAUTHORIZED)
      public ResponseEntity<String> handeleSecurityException(
          SecurityException exception
      ) {
          return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(exception.getMessage());
      }
  
      @ExceptionHandler(UnsupportedOperationException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      public ResponseEntity<String> handeleUnsupportedOperationException(
          UnsupportedOperationException exception
      ) {
          return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(exception.getMessage());
      }
}