package com.taskmanger.taskmanagermicrokernel.core;

import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.NotificationResponse;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;

public interface NotificationPlugin {
    void sendNotification(NotificationRequest notificationRequest);
}
