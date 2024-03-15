package com.example.demo.controller;

import com.example.demo.entity.Tasks;
import com.example.demo.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/pending")
    public List<Tasks> getAllPendingTasks() {
        return tasksService.getAllPendingTasks();
    }

    @PostMapping
    public Tasks createTask(@RequestBody Tasks task) {
        return tasksService.createTask(task);
    }

    @PutMapping("/{id}")
    public Tasks updateTask(@PathVariable Long id, @RequestBody Tasks task) {
        return tasksService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
    }
}
