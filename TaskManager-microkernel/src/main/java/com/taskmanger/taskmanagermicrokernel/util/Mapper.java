package com.taskmanger.taskmanagermicrokernel.util;

import com.taskmanger.taskmanagermicrokernel.data.model.Audit;
import com.taskmanger.taskmanagermicrokernel.data.model.EntityTypes;
import com.taskmanger.taskmanagermicrokernel.data.model.EventTypes;
import com.taskmanger.taskmanagermicrokernel.data.model.Users;
import com.taskmanger.taskmanagermicrokernel.dtos.request.RegistrationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.RegistrationResponse;
import com.taskmanger.taskmanagermicrokernel.event.PasswordChangedEvent;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;

public class Mapper {
    public static Users mapRehisterRequestToUserObject(RegistrationRequest registrationRequest) {
        Users users = new Users();
        users.setEmail(registrationRequest.getEmail());
        users.setPassword(registrationRequest.getPassword());
        users.setUsername(registrationRequest.getUsername());
        users.setNumber(registrationRequest.getNumber());
        return users;
    }

    public static RegistrationResponse mapUserToResponse(Users users) {
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setEmail(users.getEmail());
        registrationResponse.setId(users.getId());
        registrationResponse.setUsername(users.getUsername());
        return registrationResponse;
    }

    public static Object mapUserRegisterEvent(Users users) {
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setEmail(users.getEmail());
        userRegisteredEvent.setUsername(users.getUsername());
        return userRegisteredEvent;
    }

    public static Audit mapRegisterUserAuditLog(Users users) {
        Audit audit = new Audit();
        audit.setAction(EventTypes.USER_REGISTERED);
        audit.setBeforeState(null);
        audit.setAfterState(users.toString());
        audit.setEntityId(users.getId());
        audit.setEntityAffected(EntityTypes.USER);
        return audit;
    }

    public static Audit mapAuditToNotification() {
        Audit audit = new Audit();
        audit.setAction(EventTypes.NOTIFICATION_SENT);
        audit.setAfterState("notification sent");
        audit.setEntityAffected(EntityTypes.USER);
        audit.setBeforeState(null);
        audit.setCreatedBy("notification system");
        return audit;
    }

    public static Audit mapAuditToNotification(PasswordChangedEvent passwordChangedEvent) {
        Audit audit = new Audit();
        audit.setAction(EventTypes.PASSWORD_CHANGED);
        audit.setAfterState("Password changed successfully");
        audit.setEntityAffected(EntityTypes.USER);
        audit.setBeforeState("old password");
        audit.setCreatedBy("notification system");
        return audit;
    }
}
