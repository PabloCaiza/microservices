package com.quesito.clients.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message,
        String name

) {
}
