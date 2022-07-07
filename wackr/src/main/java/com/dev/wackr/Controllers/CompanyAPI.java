package com.dev.wackr.Controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.lang.SecurityException;

import com.dev.wackr.Repos.*;
import com.dev.wackr.Tables.*;

@RestController
@RequestMapping(path = "/wackr/api/company")
public class CompanyAPI {

    @Autowired
    private UserRepo userRep;

    @Autowired
    public CompanyRepo comRepo;


    @GetMapping("/get")
    public @ResponseBody Iterable<LSRTable> findAllCompanies(){
        return comRepo.findAll();
    }


    @PostMapping("/add/")
    public @ResponseBody Iterable<LSRTable> addCompany(@RequestBody LSRTable company){


        comRepo.save(company);

        return findAllCompanies();
    }

    @DeleteMapping("/delete")
    public @ResponseBody List<LSRTable> deleteCompany(@RequestParam long uid){
        comRepo.deleteById(uid);
        return comRepo.findAll();
    }     
    
    @GetMapping("/get/{uniqueUserId}")
    public @ResponseBody LSRTable findCompanyById(@PathVariable long uniqueUserId){

        Optional<LSRTable> resp = comRepo.findById(uniqueUserId);

        if (!resp.isPresent()){
            throw new SecurityException("Company not found!");
        }

        return resp.get();
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