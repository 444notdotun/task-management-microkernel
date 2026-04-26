package com.taskmanger.taskmanagermicrokernel.service;

import com.taskmanger.taskmanagermicrokernel.data.model.Audit;
import com.taskmanger.taskmanagermicrokernel.data.model.Users;
import com.taskmanger.taskmanagermicrokernel.data.repository.AuditLog;
import com.taskmanger.taskmanagermicrokernel.data.repository.UserRepository;
import com.taskmanger.taskmanagermicrokernel.dtos.request.ChangePasswordRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.request.RegistrationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.ChangePasswordResponse;
import com.taskmanger.taskmanagermicrokernel.dtos.response.RegistrationResponse;
import com.taskmanger.taskmanagermicrokernel.event.PasswordChangedEvent;
import com.taskmanger.taskmanagermicrokernel.exception.UsersFoundException;
import com.taskmanger.taskmanagermicrokernel.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLog auditLog;
    @Autowired
    ApplicationEventPublisher  applicationEventPublisher;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()){
            throw  new UsersFoundException("user already exist");
        }
       Users users = Mapper.mapRehisterRequestToUserObject(registrationRequest);
        userRepository.save(users);
        applicationEventPublisher.publishEvent(Mapper.mapUserRegisterEvent(users));
        auditLog(users);
        return Mapper.mapUserToResponse(users);
    }

    @Override
    public ChangePasswordResponse changePassword(long id, ChangePasswordRequest changePasswordRequest) {
       Users users= userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
       ValidateOldPassword(users,changePasswordRequest.getOldPassword());
       users.setPassword(changePasswordRequest.getNewPassword());
       userRepository.save(users);
       applicationEventPublisher.publishEvent(modelMapper.map(users, PasswordChangedEvent.class));
       return new ChangePasswordResponse("password changed successfully");
    }


    private void auditLog(Users users){
        Audit audit = Mapper.mapRegisterUserAuditLog(users);
        auditLog.save(audit);
    }

    private void ValidateOldPassword(Users users,String oldPassword){
        if(!users.getPassword().equals(oldPassword)){
            throw  new RuntimeException("old password is incorrect");
        }

    }
}
