package com.ToDo.ToDo.controller;

import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.model.TaskDtoResponse;
import com.ToDo.ToDo.model.TaskEntity;
import com.ToDo.ToDo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<TaskDtoResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @PostMapping
    public ResponseEntity<TaskDtoResponse> addTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskDtoResponse> updateTaskById(@PathVariable Long id,
                                                     @RequestBody TaskDto taskDto) throws Exception {
        TaskDtoResponse updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updatedTask);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
