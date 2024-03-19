package com.example.demo.service;

//import com.example.demo.entity.Status;
//import com.example.demo.entity.Tasks;
//import com.example.demo.repository.TasksRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TasksService {
//
//    @Autowired
//    private TasksRepository tasksRepository;
//
//    public Page<Tasks> getAllTasks(Pageable pageable) {
//        return tasksRepository.findAll(pageable);
//    }
//
//    public Tasks createTask(Tasks task) {
//        return tasksRepository.save(task);
//    }
//
//    public Tasks updateTask(Long id, Tasks task) {
//        task.setTaskId(id);
//        return tasksRepository.save(task);
//    }
//
//    public Page<Tasks> getAllPendingTasks(Pageable pageable) {
//        return tasksRepository.findByStatus(Status.PENDING, pageable);
//    }
//
//    public Page<Tasks> getAllCompletedTasks(Pageable pageable) {
//        return tasksRepository.findByStatus(Status.COMPLETED, pageable);
//    }
//
//    public void deleteTask(Long id) {
//        tasksRepository.deleteById(id);
//    }
//}

import com.example.demo.entity.Status;
import com.example.demo.entity.Tasks;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public Page<Tasks> getAllTasks(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    public Tasks createTask(Tasks task) {
        return tasksRepository.save(task);
    }

    public Tasks updateTask(Long id, Tasks task) {
        Tasks existingTask = tasksRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        task.setTaskId(id);
        return tasksRepository.save(task);
    }

    public Page<Tasks> getAllPendingTasks(Pageable pageable) {
        return tasksRepository.findByStatus(Status.PENDING, pageable);
    }

    public Page<Tasks> getAllCompletedTasks(Pageable pageable) {
        return tasksRepository.findByStatus(Status.COMPLETED, pageable);
    }

    public void deleteTask(Long id) {
        if (!tasksRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        tasksRepository.deleteById(id);
    }
}
