package com.ToDo.ToDo.service;

import com.ToDo.ToDo.entity.TaskEntity;
import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskDto addTask(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(taskDto.name());
        taskRepository.save(taskEntity);
        return taskDto;
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskEntity updateTask(Long id, TaskEntity task) {
        TaskEntity taskEntity = taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The id: %d is wrong and this task does not exist", id)));
        taskEntity.setName(task.getName());
        taskEntity.setId(task.getId());
        return taskRepository.save(taskEntity);
    }
}
///

