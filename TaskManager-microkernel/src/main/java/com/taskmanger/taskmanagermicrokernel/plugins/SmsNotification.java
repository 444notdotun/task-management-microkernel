package com.taskmanger.taskmanagermicrokernel.plugins;

import com.taskmanger.taskmanagermicrokernel.core.NotificationPlugin;
import com.taskmanger.taskmanagermicrokernel.dtos.request.NotificationRequest;
import com.taskmanger.taskmanagermicrokernel.event.PasswordChangedEvent;
import com.taskmanger.taskmanagermicrokernel.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Slf4j
@Component(value = "SMS_NOTIFICATION")
public class SmsNotification  implements NotificationPlugin {
    @Value("${termii.api.key}")
    private String apiKey;

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        String url = "https://v3.api.termii.com/api/sms/send";
        String body = String.format("{\r\n \"to\": \"%s\",\r\n \"from\":\"%s\",\r\n  \"sms\": \"HI %s, PASSWORD CHANGED SUCCESFULLY \",\r\n \"type\":\"plain\",\r\n   \"api_key\": \"%s\"\r\n,\r\n  \"channel\":\"dnd\"}",notificationRequest.getNumber(),"2348149048149",notificationRequest.getUsername(),apiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200){
                log.info("sms api error..didnt return 200{}", response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
