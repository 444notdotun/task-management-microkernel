package com.taskmanger.taskmanagermicrokernel.dtos.response;

import lombok.Data;

@Data
public class RegistrationResponse {
    private long id;
    private String  Username;
    private String  Email;
}
