package com.ToDo.ToDo.controller;

import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.service.TaskService;
import com.ToDo.ToDo.entity.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTask() {
        List<TaskEntity> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        TaskDto addedTask = taskService.addTask(taskDto);
        return ResponseEntity.ok().body(addedTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTaskById(@PathVariable Long id,
                                                     @RequestBody TaskEntity task){
        TaskEntity updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}