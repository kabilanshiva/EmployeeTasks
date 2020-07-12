package com.employee.tasks.dao.impl;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.employee.tasks.dao.EmployeeTasksDao;
import com.employee.tasks.model.Task;
import com.employee.tasks.rowmapper.TaskRowMapper;

@PropertySource(value = "classpath:application.properties")
@Configuration
public class EmployeeTaskDaoImpl extends JdbcDaoSupport implements EmployeeTasksDao {

	@Value("${insert_task_query}")
	private String insertTaskQuery;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TaskRowMapper taskRowMapper;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void insertTask(Task task) {
		getJdbcTemplate().update(insertTaskQuery, new Object[] { task.getTaskId(), task.getCreatedBy(),
				task.getAssignedTo(), task.getTaskTitle(), task.getTaskDescription(), task.getTaskStatus() });

	}

	@Override
	public List<Task> getAllTasks() {
		String getAllTaskQuery = "SELECT * FROM EmployeeTasks";
		return getJdbcTemplate().query(getAllTaskQuery,taskRowMapper);
	}

	@Override
	public void deleteTask(Task task) {
		String deleteQuery = "DELETE FROM EmployeeTasks WHERE task_id=?";
		getJdbcTemplate().update(deleteQuery,task.getTaskId());
	}

}
