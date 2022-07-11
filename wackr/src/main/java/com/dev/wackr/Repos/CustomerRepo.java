package com.dev.wackr.Repos;

import com.dev.wackr.Tables.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
    
	public Customer getCustomerByEmail(String email);
	
}