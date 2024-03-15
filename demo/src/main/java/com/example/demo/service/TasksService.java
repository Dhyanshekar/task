package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Tasks;
import com.example.demo.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks createTask(Tasks task) {
        return tasksRepository.save(task);
    }

    public Tasks updateTask(Long id, Tasks task) {
        task.setTaskId(id);
        return tasksRepository.save(task);
    }

    public List<Tasks> getAllPendingTasks() {
        return tasksRepository.findByStatus(Status.PENDING);
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }
}
