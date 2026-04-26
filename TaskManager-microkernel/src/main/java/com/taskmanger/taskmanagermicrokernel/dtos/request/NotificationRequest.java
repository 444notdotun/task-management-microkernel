package com.taskmanger.taskmanagermicrokernel.dtos.request;

import com.taskmanger.taskmanagermicrokernel.data.model.NotificationPlugInTypes;
import lombok.Data;

@Data
public class NotificationRequest {
    private String Username;
    private String number;
    private String email;
    private String senderMail;
    private NotificationPlugInTypes notificationPlugInTypes;
}
