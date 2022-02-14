package com.quesito.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record RabbitMQMessageProducer(AmqpTemplate amqpTemplate) {

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingLey {} Payload {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Publishing to {} using routingLey {} Payload {}", exchange, routingKey, payload);


    }

}
