package com.taskmanger.taskmanagermicrokernel.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  username;
    private String  Password;
    @Column(unique = true)
    private String  email;
    @Column(unique = true)
    private String  number;
}
