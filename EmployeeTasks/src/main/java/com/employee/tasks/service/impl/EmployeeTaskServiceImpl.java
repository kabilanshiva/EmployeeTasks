package com.employee.tasks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.tasks.dao.EmployeeTasksDao;
import com.employee.tasks.model.Task;
import com.employee.tasks.service.EmployeeTaskService;


@RestController
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

	@Autowired
	private EmployeeTasksDao employeeTasksDao;
	
	@Override
	public void insertTask(Task task) {
		employeeTasksDao.insertTask(task);
	}

	@Override
	public ResponseEntity<List<Task>> getAllTasks() {
		return new ResponseEntity<List<Task>>(employeeTasksDao.getAllTasks(),HttpStatus.OK);
	}

	@Override
	public void deleteTask(Task task) {
		employeeTasksDao.deleteTask(task);		
	}

}
