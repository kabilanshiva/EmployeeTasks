package com.employee.tasks.dao;

import java.util.List;

import com.employee.tasks.model.Task;

public interface EmployeeTasksDao {
	
	public void insertTask(Task task);

	public List<Task> getAllTasks();

	public void deleteTask(Task task);

}
