package com.employee.tasks.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.employee.tasks.model.Task;

@Component("taskRowMapper")
public class TaskRowMapper implements RowMapper<Task>{

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		task.setTaskId(rs.getString("task_id"));
		task.setCreatedBy(rs.getString("created_by"));
		task.setAssignedTo(rs.getString("assigned_to"));
		task.setTaskTitle(rs.getString("task_title"));
		task.setTaskDescription(rs.getString("task_description"));
		task.setTaskStatus(rs.getString("task_status"));
		return task;
	}

}
