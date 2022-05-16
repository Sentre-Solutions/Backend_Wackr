//UserTables.java


package com.dev.wackr.Tables;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.time.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

@Entity (name = "User")
public class User {

    public User(){
        this.createdOn = LocalDate.now();
    }

    /**
     * Unique User Id
     */
    @Id
    @Column(name = "UserID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long UserID;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;
    
    @Column(name = "PERMISSIONS")
    private long permissions;
    

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column(name = "CREATED_ON")
    private LocalDate createdOn;
    
    


    /*-----------------------------------   Getters and Setters --------------------------*/

    public long getUUID(){
        return UserID;
    }

    public void setUUID(long UUID){
        this.UserID = UUID;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLoginAttempts(){
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts){
        this.loginAttempts = loginAttempts;
    }


    public LocalDate getCreatedOn(){
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn){
        this.createdOn = createdOn;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public void setPermissions(long permissions){ 
    	this.permissions = permissions;
    }
    
    public long getPermissions() {
    	return this.permissions;
    }
    
    public void UpdateUser(User UpdatedUser) {
    	
    	//Everything but password, UUID

   	if(UpdatedUser.getName() != null) {
   		
  		this.name = UpdatedUser.getName();
  	}
   	
   	if(UpdatedUser.getEmail() != null) {
   		
   		this.email = UpdatedUser.getEmail();
   	}
   	
   	if((Long)UpdatedUser.getPermissions() != null) {
   		
   		this.permissions = UpdatedUser.getPermissions();
   	}
   	
   	if(UpdatedUser.getRole() != null) {
   		
   		this.role = UpdatedUser.role;
   	}
   	
   	if((Integer)UpdatedUser.getLoginAttempts() != null) {
   		
   		this.loginAttempts = UpdatedUser.getLoginAttempts();
   	}
   	
   	if(UpdatedUser.getCreatedOn() != null) {
   		
   		this.createdOn = UpdatedUser.getCreatedOn();
   	}
   
    	
    	
    }
    
}