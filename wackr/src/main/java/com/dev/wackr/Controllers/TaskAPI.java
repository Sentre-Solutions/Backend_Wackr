package com.dev.wackr.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.dev.wackr.Repos.TaskRepo;
import com.dev.wackr.Repos.UserRepo;
import com.dev.wackr.Tables.Task;
import com.dev.wackr.Tables.User;

@RestController
@RequestMapping(path = "/wackr/api/task")
public class TaskAPI {
	
	

    @Autowired
    private UserRepo userRep;

    @Autowired
    public TaskRepo taskRepo;


    @GetMapping("/get")
    public @ResponseBody Iterable<Task> findAllTasks(){
        return taskRepo.findAll();
    }


    @PostMapping("/add")
    public @ResponseBody Task addTask(@RequestBody Task task){

    	
        taskRepo.save(task);

        return task;
    }

    @DeleteMapping("/delete")
    public @ResponseBody List<Task> deleteTask(@RequestParam long uid){
        taskRepo.deleteById(uid);
        return taskRepo.findAll();
    }     
    
    @GetMapping("/get/{taskID}")
    public @ResponseBody Task findTaskById(@PathVariable long taskID){

        Optional<Task> resp = taskRepo.findById(taskID);

        if (!resp.isPresent()){
            throw new SecurityException("Task not found!");
        }

        return resp.get();
    }

      // -----------   Exception handlers, Spring boot will call these if any of the above methods return an exception specified in the "ExceptionHandler" annotation

      @ExceptionHandler(SecurityException.class)
      @ResponseStatus(HttpStatus.UNAUTHORIZED)
      public ResponseEntity<String> handeleSecurityException(
          SecurityException exception
      ) {
          return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(exception.getMessage());
      }
  
      @ExceptionHandler(UnsupportedOperationException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      public ResponseEntity<String> handeleUnsupportedOperationException(
          UnsupportedOperationException exception
      ) {
          return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(exception.getMessage());
      }

}
