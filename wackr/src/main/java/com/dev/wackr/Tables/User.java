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
    @Column(name = "UUID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long UUID;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "CREATED_ON")
    private LocalDate createdOn;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "User_Company", 
        joinColumns = { @JoinColumn(name = "UUID") }, 
        inverseJoinColumns = { @JoinColumn(name = "UCID") }
    )
    private List<Company> employers;

    /*-----------------------------------   Getters and Setters --------------------------*/

    public long getUUID(){
        return UUID;
    }

    public void setUUID(long UUID){
        this.UUID = UUID;
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

    public boolean getIsActive(){
        return active;
    }

    public void setIsActive(boolean active){
        this.active = active;
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
}