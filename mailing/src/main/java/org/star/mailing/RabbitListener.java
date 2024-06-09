package org.star.mailing;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitListener {
    static public final String queueName = "mailingQueue";
    static long counter = 0;
    private final MailService mailService;

    @Bean
    public Queue mailingQueue() {
        return new Queue(queueName, false);
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = queueName)
    public void listen(String message) {
        mailService.sendRegistration(message);
        System.out.println(message + ++counter);
    }
}
