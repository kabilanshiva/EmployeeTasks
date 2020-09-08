
#Spring security 
#Spring boot
#Spring JDBC 
#PostgreSQL

# EmployeeTasks
Microservice for creating, updating and deleting tasks for employees in an organization

#Get /employee/tasks
service without any arguments retrieves all tasks

#Put /employee/tasks
service consumes a Json input to Insert a new task

#Delete /employee/tasks
service with a Json input as Task Id deletes the coresponding task in the db

#Post /employee/tasks
service with a Json Input to update task status with task id and status as input

#Post /employee/tasks/filter Service with a Json Input with any existing fields to filter out the tasks
