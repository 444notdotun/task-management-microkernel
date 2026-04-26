package com.taskmanger.taskmanagermicrokernel.event;

import com.taskmanger.taskmanagermicrokernel.data.repository.UserRepository;
import lombok.Data;

@Data
public class UserRegisteredEvent {
    private String Username;
    private String Email;
}
