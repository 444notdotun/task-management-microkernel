package com.taskmanger.taskmanagermicrokernel.service;

import com.taskmanger.taskmanagermicrokernel.dtos.request.ChangePasswordRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.request.RegistrationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.ChangePasswordResponse;
import com.taskmanger.taskmanagermicrokernel.dtos.response.RegistrationResponse;

public interface UserService {
    RegistrationResponse registerUser(RegistrationRequest registrationRequest);
    ChangePasswordResponse changePassword(long id ,ChangePasswordRequest changePasswordRequest);
}
