package com.taskmanger.taskmanagermicrokernel.event;

import lombok.Data;

@Data
public class PasswordChangedEvent {
    private String Username;
    private String number;
}
