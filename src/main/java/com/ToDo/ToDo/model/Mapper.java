package com.ToDo.ToDo.model;


public class Mapper {
    public static TaskDto toDto(TaskEntity taskEntity) {
        return new TaskDto(taskEntity.getTitle(), taskEntity.getDescription());
    }

    public static TaskEntity toEntity(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDto.title());
        taskEntity.setDescription(taskDto.description());
        return taskEntity;
    }

}
