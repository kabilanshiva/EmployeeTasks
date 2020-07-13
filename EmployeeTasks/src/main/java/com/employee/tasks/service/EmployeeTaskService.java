package com.employee.tasks.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.employee.tasks.model.Task;

public interface EmployeeTaskService {
	
	@PutMapping("/employee/tasks")
	public void insertTask(@RequestBody Task task);
	
	@PostMapping("/employee/tasks")
	public void updateTask(@RequestBody Task task);
	
	@PostMapping("/employee/tasks/filter")
	public ResponseEntity<List<Task>> getFilteredTasks(@RequestBody Task task);
	
	@GetMapping("/employee/tasks")
	public ResponseEntity<List<Task>> getAllTasks();
	
	@DeleteMapping("/employee/tasks")
	public void deleteTask(@RequestBody Task task);

}
