package com.taskmanger.taskmanagermicrokernel.consumer;

import com.taskmanger.taskmanagermicrokernel.core.NotificationManager;
import com.taskmanger.taskmanagermicrokernel.data.model.NotificationPlugInTypes;
import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.event.PasswordChangedEvent;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConsumer {
    @Autowired
    private NotificationManager notificationManager;
    ModelMapper modelMapper = new ModelMapper();
    @EventListener
    @Async
        public void sendEmail(UserRegisteredEvent userRegisteredEvent){
        NotificationRequest notificationRequest = modelMapper.map(userRegisteredEvent, NotificationRequest.class);
        notificationRequest.setNotificationPlugInTypes(NotificationPlugInTypes.EMAIL_NOTIFICATION);
        notificationManager.sendNotification(notificationRequest);
    }
    @EventListener
    @Async
    public void sendSms(PasswordChangedEvent passwordChangedEvent){
        NotificationRequest notificationRequest = modelMapper.map(passwordChangedEvent, NotificationRequest.class);
        notificationRequest.setNotificationPlugInTypes(NotificationPlugInTypes.SMS_NOTIFICATION);
        notificationManager.sendNotification(notificationRequest);
    }


}
