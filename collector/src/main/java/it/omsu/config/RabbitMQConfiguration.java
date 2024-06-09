package it.omsu.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    static public final String queueName = "mailingQueue";
    static public final String exchangeName = "mailingExchange";
    static public final String mailKey = "mail.key";

    @Bean
    public Queue mailingQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Exchange mailingExchange() {
        return new TopicExchange(exchangeName, false, false);
    }

    @Bean
    public Binding bind(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(mailKey).noargs();
    }
}
