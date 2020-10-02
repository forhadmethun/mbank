package com.forhadmethun.accountservice.config;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${queue.account.name}")
    private String QUEUE_ACCOUNT;

    @Value("${queue.transaction.name}")
    private String QUEUE_TRANSACTION;

    @Value("${topic.exchange}")
    private String EXCHANGE;

    @Value("${exchange.queue.account.routing.key}")
    public String ROUTING_KEY_ACCOUNT;

    @Value("${exchange.queue.transaction.routing.key}")
    public String ROUTING_KEY_TRANSACTION;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    Queue queueAccount() {
        return new Queue(QUEUE_ACCOUNT);
    }

    @Bean
    Queue queueTransaction() {
        return new Queue(QUEUE_TRANSACTION);
    }

    @Bean
    Binding bindingAccount(TopicExchange exchange) {
        return BindingBuilder.bind(queueAccount()).to(exchange).with(ROUTING_KEY_ACCOUNT);
    }

    @Bean
    Binding bindingTransaction(TopicExchange exchange) {
        return BindingBuilder.bind(queueTransaction()).to(exchange).with(ROUTING_KEY_TRANSACTION);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
