package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.Mapper;
import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.model.TaskDtoResponse;
import com.ToDo.ToDo.model.TaskEntity;
import com.ToDo.ToDo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public List<TaskDtoResponse> getAllTasks() {
        List<TaskEntity> tasksEntity = taskRepository.findAll();
        return tasksEntity.stream()
                .map(Mapper::toDtoResponse)
                .toList();
    }


    public TaskDtoResponse addTask(TaskDto taskDto) {
        TaskEntity taskEntity = Mapper.toEntity(taskDto);
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return Mapper.toDtoResponse(savedEntity);
    }


    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


    public TaskDtoResponse updateTask(Long id, TaskDto taskDto) {
        TaskEntity taskEntity = taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The id: %d is wrong and this task does not exist", id)));
        taskEntity.setTitle(taskDto.title());
        taskEntity.setDescription(taskDto.description());
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return Mapper.toDtoResponse(savedEntity);
    }
}

