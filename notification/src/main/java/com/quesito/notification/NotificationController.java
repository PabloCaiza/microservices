package com.quesito.notification;

import com.quesito.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public record NotificationController(NotificationService notificationService) {
    
    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        notificationService.send(notificationRequest);
    }
}
