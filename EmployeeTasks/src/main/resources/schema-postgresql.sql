DROP TABLE IF EXISTS EmployeeTasks;

CREATE TABLE EmployeeTasks
(
    task_id text PRIMARY KEY NOT NULL,
    created_by text,
    assigned_to text,
    task_title text,
    task_description text,
    task_status text
);
