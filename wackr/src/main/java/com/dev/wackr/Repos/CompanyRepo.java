package com.dev.wackr.Repos;

import com.dev.wackr.Tables.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<LSRTable, Long>{
    

}