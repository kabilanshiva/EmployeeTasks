package com.employee.tasks.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
		return getJdbcTemplate().query(getAllTaskQuery, taskRowMapper);
	}

	@Override
	public void deleteTask(Task task) {
		String deleteQuery = "DELETE FROM EmployeeTasks WHERE task_id=?";
		getJdbcTemplate().update(deleteQuery, task.getTaskId());
	}

	@Override
	public void updateTask(Task task) {
		String updateQuery = "UPDATE EmployeeTasks SET task_status = ? WHERE task_id=?";
		getJdbcTemplate().update(updateQuery, new Object[] { task.getTaskStatus(), task.getTaskId() });
	}

	@Override
	public List<Task> getFilteredTasks(Task task) {
		Map<Integer,String> parameters= new TreeMap<>();
		int index = 0;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM EmployeeTasks WHERE ");
		if (task.getTaskId() != null && !task.getTaskId().isEmpty()) {
			stringBuilder.append("task_id = ? AND ");
			parameters.put(index, task.getTaskId());
			index+=1;
		}
		if (task.getCreatedBy() != null && !task.getCreatedBy().isEmpty()) {
			stringBuilder.append("created_by = ? AND ");
			parameters.put(index, task.getCreatedBy());
			index+=1;
		}
		if (task.getAssignedTo() != null && !task.getAssignedTo().isEmpty()) {
			stringBuilder.append("assigned_to = ? AND ");
			parameters.put(index, task.getAssignedTo());
			index+=1;
		}
		if (task.getTaskTitle() != null && !task.getTaskTitle().isEmpty()) {
			stringBuilder.append("task_title = ? AND ");
			parameters.put(index, task.getTaskTitle());
			index+=1;
		}
		if (task.getTaskDescription() != null && !task.getTaskDescription().isEmpty()) {
			stringBuilder.append("task_description = ? AND ");
			parameters.put(index, task.getTaskDescription());
			index+=1;
		}
		if (task.getTaskStatus() != null && !task.getTaskStatus().isEmpty()) {
			stringBuilder.append("task_status = ? AND ");
			parameters.put(index, task.getTaskStatus());
		}

		Object[] args = new Object[parameters.size()];
		
		for (Entry<Integer, String> entry : parameters.entrySet())
			args[entry.getKey()] = entry.getValue();

		String filterQuery = stringBuilder.toString();
		filterQuery = filterQuery.substring(0, filterQuery.length() - 4);
		return getJdbcTemplate().query(filterQuery, args, taskRowMapper);
	}

}
