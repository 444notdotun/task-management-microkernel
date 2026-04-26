package com.taskmanger.taskmanagermicrokernel.plugins;

import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GmailSenderTest {

    @Autowired
    GmailSender gmailSender;



    @Test
    void testThatSendEmailWorks(){
        NotificationRequest userRegisteredEvent = new NotificationRequest();
        userRegisteredEvent.setEmail("adedotunStephen8@gmail.com");
        userRegisteredEvent.setUsername("Adedotun");
        userRegisteredEvent.setSenderMail("adedortmahan@gmail.com");
        gmailSender.sendNotification(userRegisteredEvent);
    }
}