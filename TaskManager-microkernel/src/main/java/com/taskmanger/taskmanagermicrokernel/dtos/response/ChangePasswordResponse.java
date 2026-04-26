package com.taskmanger.taskmanagermicrokernel.dtos.response;

import lombok.Data;

@Data
public class ChangePasswordResponse {
    private String message;

    public ChangePasswordResponse(String passwordChangedSuccessfully) {
        this.message = passwordChangedSuccessfully;
    }
}
