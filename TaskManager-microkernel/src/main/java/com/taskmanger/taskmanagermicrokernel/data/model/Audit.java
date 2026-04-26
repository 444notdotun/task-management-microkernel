package com.taskmanger.taskmanagermicrokernel.data.model;

import jakarta.persistence.*;
import jdk.jfr.EventType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String createdBy;
    private LocalDateTime timeOfAction;
    private EventTypes action;
     private EntityTypes entityAffected;
     private  long entityId;
     private String beforeState;
     private String afterState;

     @PrePersist
    protected void onCreate() {
         timeOfAction = LocalDateTime.now();
     }
}
