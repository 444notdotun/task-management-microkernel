package com.taskmanger.taskmanagermicrokernel.dtos.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
