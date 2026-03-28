package re.btvn02.repository;

import org.springframework.stereotype.Repository;
import re.btvn02.enums.Priority;
import re.btvn02.models.Task;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(
                    String.valueOf(i),
                    "Task " + i,
                    "Desc " + i,
                    Priority.HIGH,
                    "1"
            ));
        }
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task save(Task task) {
        if (findById(task.getId()) != null) {
            return null;
        }
        tasks.add(task);
        return task;
    }

    public Task update(String id, Task newTask) {
        Task oldTask = findById(id);
        if (oldTask == null) return null;

        oldTask.setTitle(newTask.getTitle());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setPriority(newTask.getPriority());
        oldTask.setAssignedTo(newTask.getAssignedTo());

        return oldTask;
    }

    public Task deleteById(String id) {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
        }
        return task;
    }
}
