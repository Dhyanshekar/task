package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @NotNull
    private Status status;

    @Column
    @Future
    private LocalDateTime deadlineDate;

    @Column
    @NotNull
    private String task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
