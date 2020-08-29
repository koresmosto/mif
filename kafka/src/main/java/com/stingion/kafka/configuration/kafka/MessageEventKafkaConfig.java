/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.configuration.kafka;

import com.stingion.kafka.event.MessageEvent;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;

@SuppressFBWarnings({"SIC_INNER_SHOULD_BE_STATIC_ANON", "UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS"})
@Slf4j
@Configuration
public class MessageEventKafkaConfig extends EventKafkaConfig<MessageEvent> {

    public MessageEventKafkaConfig() {
        super(MessageEvent.class);
    }

    @SuppressWarnings("CPD-START")
    @Profile("!test")
    @Bean("messageTopic")
    public NewTopic createTopic(@Value("${spring.kafka.topic.message}") String topicName) {
        return new NewTopic(topicName, 2, (short) 2);
    }

    @Profile("!test")
    @Bean("messageListener")
    @DependsOn("messageTopic")
    public Object listener() {
        return new Object() {

            @KafkaListener(topics = "${spring.kafka.topic.message}", groupId = "${spring.kafka.group-id}")
            public void consume(MessageEvent message) {
                log.info("Consumed message () -> {}", message);
            }
        };
    }
}
