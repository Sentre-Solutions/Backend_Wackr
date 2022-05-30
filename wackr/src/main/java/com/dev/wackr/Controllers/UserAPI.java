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


    public User doLogin(User user){
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("User");

        if (user == null) {
            throw new SecurityException("Could not create user.");
        }
        userRep.save(user);
        return user;
    }

    // Mapping for main site add user request
    @PostMapping(
        path = "/new",
        consumes = "*/*")
    public ModelAndView addUser ( User user ){

        doLogin(user);
        return new ModelAndView("redirect:/index.html");  

    }

    // This is the mapping mobile add user request
    @PostMapping(path = "/add")
    public @ResponseBody User addAPIUser ( @RequestBody User user ){

        doLogin(user);
        return user; 

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
    
    @GetMapping("/GetByEmail")
    public @ResponseBody User findUserByEmail(@RequestBody User User){

       User resp = userRep.getUserByEmail(User.getEmail()); 	

        return resp;		
    
    }
    
    @PostMapping("/Update")
    public @ResponseBody  User UpdateUser(@RequestBody User UpdatedUser) {
    	
    	  Optional<User> resp = userRep.findById(UpdatedUser.getUUID());
    	  
    	   if (!resp.isPresent()){
    		   
               throw new SecurityException("User not found!");
           }
    	   
    	   User Myresp = resp.get();
    	   
    	   Myresp.UpdateUser(UpdatedUser);
    	   
    	   userRep.save(Myresp);
   
    	return  Myresp;
    }
    
    @PostMapping("/AddTask/{uniqueUserId}")
    public @ResponseBody  User AddTask(@RequestBody Task MyTask, @PathVariable long uniqueUserId) {
    	
    	  Optional<User> resp = userRep.findById(uniqueUserId);
    	  
    	   if (!resp.isPresent()){
    		   
               throw new SecurityException("User not found!");
           }
    	   
    	   User Myresp = resp.get();
    	   
    	   Myresp.Tasks.add(MyTask);
    	   
    	   userRep.save(Myresp);
   
    	return  Myresp;
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