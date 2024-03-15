package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDateTime deadlineDate;

    @Column
    private String task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
