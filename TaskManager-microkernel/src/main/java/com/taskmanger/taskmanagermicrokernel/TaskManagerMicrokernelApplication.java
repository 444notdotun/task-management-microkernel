package com.taskmanger.taskmanagermicrokernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TaskManagerMicrokernelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerMicrokernelApplication.class, args);
    }

}
