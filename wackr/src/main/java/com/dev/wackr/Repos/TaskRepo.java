package com.dev.wackr.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.wackr.Tables.Company;

import com.dev.wackr.Tables.*;

public interface TaskRepo extends JpaRepository<Task, Long>{
    

}
