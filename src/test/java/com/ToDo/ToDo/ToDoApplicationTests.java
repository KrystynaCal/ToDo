package com.ToDo.ToDo;

import com.ToDo.ToDo.model.TaskDto;
import com.ToDo.ToDo.model.TaskDtoResponse;
import com.ToDo.ToDo.model.TaskEntity;
import com.ToDo.ToDo.repository.TaskRepository;
import com.ToDo.ToDo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ToDoApplicationTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testGetAllTasks() {
        //given
        taskRepository.deleteAll();
        TaskDto taskDto1 = new TaskDto("title1", "description1");
        TaskDto taskDto2 = new TaskDto("title2", "description2");
        taskService.addTask(taskDto1);
        taskService.addTask(taskDto2);
        //when
        List<TaskDtoResponse> responseList = taskService.getAllTasks();
        //then
        assertEquals(2, responseList.size());
        assertEquals("title1", responseList.get(0).title());
        assertEquals("title2", responseList.get(1).title());
        assertEquals("description1", responseList.get(0).description());
        assertEquals("description2", responseList.get(1).description());
    }


    @Test
    public void testAddTask() {
        //given
        TaskDto taskDto = new TaskDto("title1", "description1");
        //when
        TaskDtoResponse result = taskService.addTask(taskDto);

        //then
        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("title1", result.title());
        assertEquals("description", result.description());
        TaskEntity savedEntity = taskRepository.findById(result.id()).orElse(null);
        assertNotNull(savedEntity);
        assertEquals("title1", savedEntity.getTitle());
        assertEquals("description1", savedEntity.getDescription());

    }


    @Test
    public void testDeleteTaskById() {
        //given
        TaskDto taskDto = new TaskDto("title2", "description2");
        TaskDtoResponse result = taskService.addTask(taskDto);
        //when
        taskRepository.deleteById(result.id());
        //then
        assertTrue(taskRepository.findById(result.id()).isEmpty());
    }
}
