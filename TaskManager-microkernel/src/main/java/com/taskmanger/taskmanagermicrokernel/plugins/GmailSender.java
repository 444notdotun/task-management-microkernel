package com.taskmanger.taskmanagermicrokernel.plugins;

import com.taskmanger.taskmanagermicrokernel.core.NotificationPlugin;
import com.taskmanger.taskmanagermicrokernel.data.model.NotificationPlugInTypes;
import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.dtos.response.NotificationResponse;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component(value = "EMAIL_NOTIFICATION")
public class GmailSender implements NotificationPlugin {
    @Autowired
    JavaMailSender javaMailSender;



    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
       String emailSubject = getEmailSubject();
       String body = emailBody(notificationRequest);
       try{
           javaMailSender.send(new SimpleMailMessage(){{
               setTo(notificationRequest.getEmail());
               setSubject(emailSubject);
               setFrom(notificationRequest.getSenderMail());
               setText(body);
           }});
       }catch (MailAuthenticationException e){
           throw new RuntimeException("email authentication failed");
       }

    }

    private String emailBody(NotificationRequest notificationRequest){
        return "Hi " + notificationRequest.getUsername() + ",\n\n" +
                "Welcome to TaskFlow! Your account has been " +
                "successfully created.\n\n" +
                "You can now log in and start managing your tasks.\n\n" +
                "If you did not create this account, please " +
                "contact our support team immediately.\n\n" +
                "Best regards,\n" +
                "The TaskFlow Team";
    }
    private String getEmailSubject(){
        return "Welcome to TaskFlow!";
    }
}
