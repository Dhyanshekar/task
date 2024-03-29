package com.example.demo.repository;

//import com.example.demo.entity.Status;
//import com.example.demo.entity.Tasks;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface TasksRepository extends JpaRepository<Tasks, Long> {
//    List<Tasks> findByStatus(Status status);
//}

import com.example.demo.entity.Status;
import com.example.demo.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Page<Tasks> findByStatus(Status status, Pageable pageable);
}
