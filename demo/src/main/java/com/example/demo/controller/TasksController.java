package com.example.demo.controller;

import com.example.demo.entity.Tasks;
import com.example.demo.service.TasksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping
    public Page<Tasks> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return tasksService.getAllTasks(PageRequest.of(page, size));
    }

    @GetMapping("/pending")
    public Page<Tasks> getAllPendingTasks(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size) {
        return tasksService.getAllPendingTasks(PageRequest.of(page, size));
    }

    @GetMapping("/completed")
    public Page<Tasks> getAllCompletedTasks(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size) {
        return tasksService.getAllCompletedTasks(PageRequest.of(page, size));
    }

//    @PostMapping
//    public Object createTask(@Valid @RequestBody Tasks task, BindingResult bindingResult) {
//        try {
//            if (bindingResult.hasErrors()) {
//                return bindingResult.getAllErrors();
//            }
//            return tasksService.createTask(task);
//        } catch (Exception e) {
//            return "An error occurred while creating the task: " + e.getMessage();
//        }
//    }

    @PostMapping
    public ResponseEntity<Object> createTask(@Valid @RequestBody Tasks task, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
            Object createdTask = tasksService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the task: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public Object updateTask(@PathVariable Long id, @Valid @RequestBody Tasks task, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return bindingResult.getAllErrors();
            }
            return tasksService.updateTask(id, task);
        } catch (Exception e) {
            return "An error occurred while updating the task: " + e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
    }
}
