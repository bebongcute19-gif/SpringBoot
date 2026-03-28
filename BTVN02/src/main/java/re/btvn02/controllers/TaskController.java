package re.btvn02.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.btvn02.models.Task;
import re.btvn02.service.impl.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<Task>> findAll(@RequestParam(required = false) String search) {
        List<Task> tasks= taskService.findAll();
        if (search != null) {
            tasks = tasks.stream().filter(task -> task.getTitle().contains(search)).toList();
        }
        return ResponseEntity.ok(tasks);
    }
    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        Task added = taskService.addTask(task);
        if(added == null){
            return ResponseEntity.badRequest().body("Assigned user not found");
        }
        return ResponseEntity.status(201).body(added);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @RequestBody Task task) {
        Task updated = taskService.updateTask(id, task);
        if(updated == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        Task deleted = taskService.deleteTask(id);
        if(deleted == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.noContent().build();
    }

}
