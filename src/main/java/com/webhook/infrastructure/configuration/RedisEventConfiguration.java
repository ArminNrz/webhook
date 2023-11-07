package com.webhook.infrastructure.configuration;

import com.webhook.infrastructure.event.webhook.WebhookRedisEventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@RequiredArgsConstructor
public class RedisEventConfiguration {

    private final WebhookRedisEventConsumer redisEventConsumer;

    @Bean
    RedisMessageListenerContainer keyExpirationListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.addMessageListener(redisEventConsumer, new PatternTopic("__keyevent@*__:expired"));
        return listenerContainer;
    }
}
