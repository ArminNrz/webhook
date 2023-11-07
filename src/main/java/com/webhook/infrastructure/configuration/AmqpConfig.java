package com.example.demowebhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AmqpConfig {

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper mapper;

    @Bean
    @Primary
    public AmqpTemplate amqpTemplatePrimary() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverterPrimary());
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactoryPrimary() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverterPrimary());
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @Bean
    @Primary
    public MessageConverter jacksonConverterPrimary() {
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }
}
