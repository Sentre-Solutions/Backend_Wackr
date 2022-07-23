package com.dev.wackr.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.wackr.Repos.CustomerRepo;
import com.dev.wackr.Repos.TaskRepo;
import com.dev.wackr.Tables.Customer;
import com.dev.wackr.Tables.Task;
import com.dev.wackr.Tables.User;


@RestController
@RequestMapping(path = "/wackr/api/customer")

public class CustomerAPI {
	
	@Autowired
    private CustomerRepo customerRepo;

    @Autowired
    public TaskRepo taskRepo;
    
    @GetMapping("/get")
    public @ResponseBody List<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }
    
    @PostMapping(path = "/add")
    public @ResponseBody Customer addAPIUser ( @RequestBody Customer customer ){

    	  
        customerRepo.save(customer);
		return customer;

    }


    
    @DeleteMapping("/delete")
    public @ResponseBody List<Customer> deleteUser(@RequestParam long uid){
        customerRepo.deleteById(uid);
        return customerRepo.findAll();
    }     
    
    @GetMapping("/get/{uniqueCustomerID}")
    public @ResponseBody Customer findUserById(@PathVariable long uniqueCustomerID){

        Optional<Customer> resp = customerRepo.findById(uniqueCustomerID);

        if (!resp.isPresent()){
            throw new SecurityException("User not found!");
        }

        return resp.get();
    }
    
    @GetMapping("/GetByEmail")
    public @ResponseBody Customer findUserByEmail(@RequestBody Customer customer){

       Customer resp = customerRepo.getCustomerByEmail(customer.getEmail()); 	

        return resp;		
    
    }
    
    @PostMapping("/Update")
    public @ResponseBody  Customer UpdateUser(@RequestBody Customer customer) {
    	
    	  Optional<Customer> resp = customerRepo.findById(customer.getCustomerID());
    	  
    	   if (!resp.isPresent()){
    		   
               throw new SecurityException("User not found!");
           }
    	   
    	   Customer Myresp = resp.get();
    	   
    	   Myresp.UpdateCustomer(customer);
    	   
    	   customerRepo.save(Myresp);
   
    	return  Myresp;
    }
    
    @PostMapping("/AddTask/{uniqueUserId}")
    public @ResponseBody  Customer AddTask(@RequestBody Task MyTask, @PathVariable long CustomerID) {
    	
    	  Optional<Customer> resp = customerRepo.findById(CustomerID);
    	  
    	   if (!resp.isPresent()){
    		   
               throw new SecurityException("User not found!");
           }
    	   
    	   Customer Myresp = resp.get();
    	   
    	   Myresp.Tasks.add(MyTask);
    	   
    	   customerRepo.save(Myresp);
   
    	return  Myresp;
    }
    
    @GetMapping("/get/{CustomerID}")
    public @ResponseBody Customer findCustomerById(@PathVariable long CustomerID){

        Optional<Customer> resp = customerRepo.findById(CustomerID);

        if (!resp.isPresent()){
            throw new SecurityException("User not found!");
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
