package re.btvn02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.btvn02.models.Task;
import re.btvn02.repository.TaskRepository;
import re.btvn02.service.ITaskService;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    @Autowired
     public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task addTask(Task task) {
        if(userService.findById(task.getAssignedTo()) == null){
            return null;
        }
        return taskRepository.save(task);
    }
    public Task updateTask(String id, Task task) {
        // ✅ FIX: check user tồn tại
        if (userService.findById(task.getAssignedTo()) == null) {
            return null;
        }

        return taskRepository.update(id, task);
    }
    public Task deleteTask(String id) {
        return taskRepository.deleteById(id);
    }
}
