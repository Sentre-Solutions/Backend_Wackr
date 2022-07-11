package com.dev.wackr.Tables;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity (name = "Customer")
public class Customer {
	
	
	
	public Customer() {
		 this.createdOn = LocalDate.now();
    }

    /**
     * Unique User Id
     */
    @Id
    @Column(name = "CustomerID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long CustomerID;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "CREATED_ON")
    private LocalDate createdOn;
    
    @Column (name = "ADDRESS")
    private String address;
    
    @Column(name = "NOTES")
    private String notes;
    
    @JsonIgnore
    @Nullable
    @OneToMany
    @JoinTable( name = "CUSTOMER_TASKS" , 
    	joinColumns = @JoinColumn(name = "CustomerID"), 
    	inverseJoinColumns = @JoinColumn(name = "UniqueTaskID"))
    public Set<Task> Tasks;
    
    
    
    
	public void setEmail(String email) {
	   this.email = email;
	}
	    
	public String getEmail() {
	  return email;
	}
	
	public Long getCustomerID() {
		return CustomerID;
	}
	
	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Set<Task> getTasks() {
		return Tasks;
	}
	
	public void setTasks(Set<Task> Tasks) {
		this.Tasks = Tasks;
	}
	
	 public void UpdateCustomer(Customer UpdatedCustomer) {
	    	
	    

	   	if(UpdatedCustomer.getName() != null) {
	   		
	  		this.name = UpdatedCustomer.getName();
	  	}
	   	
	   	if(UpdatedCustomer.getEmail() != null) {
	   		
	   		this.email = UpdatedCustomer.getEmail();
	   	}
	   	
	   	
	   	if(UpdatedCustomer.getCreatedOn() != null) {
	   		
	   		this.createdOn = UpdatedCustomer.getCreatedOn();
	   	}
	   	
	if(UpdatedCustomer.getTasks() != null) {
	   		
	   		this.Tasks = UpdatedCustomer.getTasks();
	   	}
	
	if(UpdatedCustomer.getNotes() != null) {
   		
   		this.notes = UpdatedCustomer.getNotes();
   	}
	
	if(UpdatedCustomer.getAddress() != null) {
   		
   		this.address = UpdatedCustomer.getAddress();
   	}
	
	
	   
	    
	    	
	    }
	 

}
