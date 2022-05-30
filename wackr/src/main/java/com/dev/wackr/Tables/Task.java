package com.dev.wackr.Tables;

import java.time.LocalDate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Task {
	
	   public Task(){
	        this.createdOn = LocalDate.now();
	    }

	    /**
	     * Unique Company Id
	     */
	    @Id
	    @Column(name = "TASK_ID")
		@GeneratedValue(strategy=GenerationType.AUTO)  //Some are public for easier debugging REMEMBER TO CHANGE TO A BETTER SYSTEM!!
		public long UniqueTaskID;

	    @Column(name = "TASK_NAME")
		public String taskName;
	    
	  
	    @Column(name = "CREATED_ON")
	    private LocalDate createdOn;
	    
	    @Column(name = "DUE_DATE")
	    private LocalDate dueDate;
	    
	    @Column(name = "TASK_DESCRIPTION")
		public String TaskDescription;
	    
	    @ManyToMany(mappedBy = "Tasks")
	    private Set<User> UserOnJob;
	    
	    
	    
	    

}
