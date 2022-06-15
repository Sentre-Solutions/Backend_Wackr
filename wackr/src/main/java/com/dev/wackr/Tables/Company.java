package com.dev.wackr.Tables;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.time.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

@Entity
public class Company {


    public Company(){
        this.createdOn = LocalDate.now();
    }

    /**
     * Unique Company Id
     */
    @Id
    @Column(name = "UCID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long UniqueCompanyID;

    @Column(name = "COMPANY_NAME")
    private String companyName;
    
  
    @Column(name = "CREATED_ON")
    private LocalDate createdOn;
    
    @OneToMany(cascade = CascadeType.ALL)
    private  Set<Billing> Bills;
    

}