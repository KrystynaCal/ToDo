package com.ToDo.ToDo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task task) {
        Task taskEntity = taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The id: %d is wrong and this task does not exist", id)));
        taskEntity.setName(task.getName());
        taskEntity.setId(task.getId());
        return taskRepository.save(taskEntity);
    }
}


