package com.employee.tasks.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.employee.tasks.model.Task;

public interface EmployeeTaskService {
	
	@PostMapping("/task")
	public void insertTask(@RequestBody Task task);
	
	@GetMapping("/task")
	public ResponseEntity<List<Task>> getAllTasks();
	
	@DeleteMapping("/task")
	public void deleteTask(@RequestBody Task task);

}
