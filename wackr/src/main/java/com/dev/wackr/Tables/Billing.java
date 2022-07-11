package com.dev.wackr.Tables;

import java.time.LocalDate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Billing {
	
	   public Billing(){
	        this.createdOn = LocalDate.now();
	    }

	    /**
	     * Unique Bill Id
	     */
	    @Id
	    @Column(name = "Billing")
		@GeneratedValue(strategy=GenerationType.AUTO)  
		private long uniqueBillingID;

	    
	  
	    @Column(name = "TRANSACTION_DATE")
	    private LocalDate createdOn;
	    
	    @Column(name = "AMOUNT")
	    private float amount;
	    
	    
	    
	    
	    @OneToOne 
	    private Task taskAccomplished;
	    
	     
	
	    
	    
	    /*-----------------------------------   Getters and Setters --------------------------*/

	    public long getUniqueBillingID(){
	        return uniqueBillingID;
	    }

	    public void setUUID(long uniqueBillingID){
	        this.uniqueBillingID = uniqueBillingID;
	    }
	    
	    public LocalDate getCreatedOn() {
	    	return createdOn;
	    }
	    
	    public void setCreatedOn(LocalDate createdOn ) {
	    	this.createdOn = createdOn;
	    }
	    
	    public float getAmount() {
	    	return amount;
	    }
	    
	    public void setAmount (float amount) {
	    	this.amount = amount;
	    }
	    
	    public Task getTaskAccomplished() {
	    	return taskAccomplished;
	    }
	    
	    public void setTaskAccomplished(Task taskAccomplished) {
	    	this.taskAccomplished = taskAccomplished;
	    }
	 
	    
	    
	    


}
