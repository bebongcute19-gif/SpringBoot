package re.btvn02.service;

import re.btvn02.models.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();
    Task addTask(Task task);
    Task updateTask(String id ,Task task);
    Task deleteTask(String id);
}
