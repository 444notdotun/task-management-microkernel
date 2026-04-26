package com.taskmanger.taskmanagermicrokernel.service;

import com.taskmanger.taskmanagermicrokernel.dtos.request.ChangePasswordRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.request.RegistrationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.ChangePasswordResponse;
import com.taskmanger.taskmanagermicrokernel.dtos.response.RegistrationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {
    @Autowired
   private UserService userService;

RegistrationRequest registrationRequest;
    ChangePasswordRequest changePasswordRequest;
@BeforeEach
void setUp() {
    registrationRequest = new RegistrationRequest();
    changePasswordRequest = new ChangePasswordRequest();


}
    @Test
    void testThatUsersCanRegister(){
        registrationRequest.setEmail("adedotunStephen8@Gmail.com");
        registrationRequest.setPassword("123456");
        registrationRequest.setUsername("adedotun");
        RegistrationResponse registrationResponse = userService.registerUser(registrationRequest);
        assertNotNull(registrationResponse);
    }

    @Test
    void testThatUserCanChangePassword(){
    registrationRequest.setEmail("adedotunStephen*@gmail.com");
    registrationRequest.setPassword("123456");
    registrationRequest.setUsername("Adedotun");
    registrationRequest.setNumber("2348149048149");
    RegistrationResponse registrationResponse = userService.registerUser(registrationRequest);
    assertNotNull(registrationResponse);
    changePasswordRequest.setOldPassword("123456");
    changePasswordRequest.setNewPassword("Icui44");
    ChangePasswordResponse changePasswordResponse = userService.changePassword(registrationResponse.getId(),changePasswordRequest);
    assertNotNull(changePasswordResponse);


    }

}