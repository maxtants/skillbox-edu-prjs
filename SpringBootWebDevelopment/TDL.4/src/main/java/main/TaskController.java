package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TaskController {

    @GetMapping("/tasks/")
    public List<Task> getTasks() {
        return Storage.getAllTasks();
    }

    @PostMapping("/tasks/")
    public AtomicInteger add(Task task) {
        return Storage.addTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable int id) {
        Storage.deleteTask(id);
    }

    @DeleteMapping("/tasks/")
    public void deleteAll() {
        Storage.deleteAll();
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable AtomicInteger taskId, Task newTask) {
        Storage.updateTask(taskId, newTask);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if(task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    public void update(Task oldTask, Task newTask) {

    }

}
