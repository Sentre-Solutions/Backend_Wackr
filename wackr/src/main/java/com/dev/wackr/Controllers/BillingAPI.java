package com.dev.wackr.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.wackr.Repos.BillingRepo;
import com.dev.wackr.Repos.CompanyRepo;
import com.dev.wackr.Repos.TaskRepo;
import com.dev.wackr.Repos.UserRepo;
import com.dev.wackr.Tables.Billing;
import com.dev.wackr.Tables.LSRTable;
import com.dev.wackr.Tables.Task;


@RestController
@RequestMapping(path = "/wackr/api/billing")

public class BillingAPI {
	
	    

	    @Autowired
	    public BillingRepo billingRepo;
	    
	    
	    @GetMapping("/get")
	    public @ResponseBody Iterable<Billing> findAllCompanies(){
	        return billingRepo.findAll();
	    }


	    @PostMapping("/add")
	    public @ResponseBody Billing addCompany(@RequestBody Billing Bill){

	    	
	    	billingRepo.save(Bill);

	        return Bill;
	    }

	    @DeleteMapping("/delete")
	    public @ResponseBody List<Billing> deleteCompany(@RequestParam long uid){
	    	billingRepo.deleteById(uid);
	        return billingRepo.findAll();
	    }     
	    
	    @GetMapping("/get/{BillingId}")
	    public @ResponseBody Billing findCompanyById(@PathVariable long BillingId){

	        Optional<Billing> resp = billingRepo.findById(BillingId);

	        if (!resp.isPresent()){
	            throw new SecurityException("Company not found!");
	        }

	        return resp.get();
	    }
	    


}
