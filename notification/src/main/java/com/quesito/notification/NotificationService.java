package com.quesito.notification;


import com.quesito.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public record NotificationService(NotificationRepository notificationRepository) {
    
    public void send(NotificationRequest notificationRequest){
          notificationRepository.save(Notification.builder()
                  .toCustomerId(notificationRequest.toCustomerId())
                  .toCustomerEmail(notificationRequest.toCustomerName())
                  .sender("Pablo")
                  .message(notificationRequest.message())
                  .sentAt(LocalDateTime.now())
                  .build());
          log.info("a notification is send");
    }
}
