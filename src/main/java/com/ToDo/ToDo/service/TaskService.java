package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.Mapper;
import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.model.TaskEntity;
import com.ToDo.ToDo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public List<TaskDto> getAllTasks() {
        List<TaskEntity> tasksEntity = taskRepository.findAll();
        return tasksEntity.stream()
                .map(Mapper::toDto)
                .toList();
    }


    public TaskDto addTask(TaskDto taskDto) {
        TaskEntity taskEntity = Mapper.toEntity(taskDto);
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return Mapper.toDto(savedEntity);
    }


    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


    public TaskEntity updateTask(Long id, TaskEntity task) {
        TaskEntity taskEntity = taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The id: %d is wrong and this task does not exist", id)));
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        return taskRepository.save(taskEntity);
    }
}

