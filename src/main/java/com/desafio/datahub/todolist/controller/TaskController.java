package com.desafio.datahub.todolist.controller;

import com.desafio.datahub.todolist.dto.TaskDto;
import com.desafio.datahub.todolist.dto.TaskPostDto;
import com.desafio.datahub.todolist.enums.TaskStatus;
import com.desafio.datahub.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskPostDto taskPostDto, @PathVariable Long userId) {

        return new ResponseEntity<>(taskService.createTask(taskPostDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TaskDto>> findAllTasks() {

        return new ResponseEntity<>(taskService.findAllTasks(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/taskId/{taskId}/userId/{userId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.deleteTaskById(taskId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<List<TaskDto>> filterTasksByStatus(@PathVariable TaskStatus status) {

        return new ResponseEntity<>(taskService.filterTaskByStatus(status), HttpStatus.OK);
    }
}
