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
		@GeneratedValue(strategy=GenerationType.AUTO)  //Some are public for easier debugging REMEMBER TO CHANGE TO A BETTER SYSTEM!!
		public long uniqueBillingID;

	    
	  
	    @Column(name = "TRANSACTION_DATE")
	    private LocalDate createdOn;
	    
	    @Column(name = "AMOUNT")
	    private float amount;
	    
	    
	    
	    
	    @OneToOne 
	    private Task taskAccomplished;
	    
	     
	    @ManyToOne
	    @JoinColumn (name = "CompanyId")
	    private  Company company;
	    
	    
	    
	    
	    
	    


}
