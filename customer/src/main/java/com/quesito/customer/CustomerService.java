package com.quesito.customer;

import com.quesito.amqp.RabbitMQMessageProducer;
import com.quesito.clients.fraud.FraudCheckResponse;
import com.quesito.clients.fraud.FraudClient;
import com.quesito.clients.notification.NotificationClient;
import com.quesito.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              NotificationClient notificationClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer) {

    public void registerCustomer(CustomerRequest customerRequest) {
        Cutomer cutomer = Cutomer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        customerRepository.saveAndFlush(cutomer);
        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse=fraudClient.isFraudster(cutomer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                cutomer.getId(),
                cutomer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode ..", cutomer.getFirstName()),
                cutomer.getFirstName());
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");



    }
}
