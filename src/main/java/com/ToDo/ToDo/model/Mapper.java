package com.ToDo.ToDo.model;


public class Mapper {
    public static TaskDto toDto(TaskEntity taskEntity){
        return new TaskDto(taskEntity.getId(), taskEntity.getTitle());
    }

}
