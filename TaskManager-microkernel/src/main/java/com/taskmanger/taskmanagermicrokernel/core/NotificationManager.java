package com.taskmanger.taskmanagermicrokernel.core;

import com.taskmanger.taskmanagermicrokernel.data.model.Audit;
import com.taskmanger.taskmanagermicrokernel.data.model.NotificationPlugInTypes;
import com.taskmanger.taskmanagermicrokernel.data.repository.AuditLog;
import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.NotificationResponse;
import com.taskmanger.taskmanagermicrokernel.event.PasswordChangedEvent;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;
import com.taskmanger.taskmanagermicrokernel.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class NotificationManager {
    @Value("${spring.mail.username}")
    private String senderEmail;
    @Autowired
    AuditLog auditLog;
    private Audit audit;


    private final Map<String, NotificationPlugin> notificationPluginMap;

    public NotificationManager(Map<String, NotificationPlugin> notificationPluginMap) {
        this.notificationPluginMap = notificationPluginMap;
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        try {
            NotificationPlugin notificationPlugin = notificationPluginMap.get(notificationRequest.getNotificationPlugInTypes().name());
            if(notificationPlugin==null){
                throw new RuntimeException(" notification plugin not found");
            }
             notificationPlugin.sendNotification(notificationRequest);

            if(notificationRequest.getNotificationPlugInTypes().equals(NotificationPlugInTypes.SMS_NOTIFICATION)){
                audit = Mapper.mapAuditToNotification();}
            else {
             audit = Mapper.mapAuditToNotification();
            }
            auditLog.save(audit);
        } catch (Exception e) {
            throw new RuntimeException(" notification failed: "+e.getMessage());
        }
    }








}
