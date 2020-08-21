/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service;

import static com.stingion.kafka.Constants.TEST_TOPIC;
import static org.assertj.core.api.Assertions.assertThat;

import com.stingion.kafka.testconfiguration.TestContextConfig;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EmbeddedKafka(topics = TEST_TOPIC)
@Import(TestContextConfig.class)
public abstract class KafkaTestInitializer {

    @Autowired
    protected String topic;

    protected BlockingQueue<ConsumerRecord<String, String>> records;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    private KafkaMessageListenerContainer<String, String> container;

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @BeforeAll
    void setup() {
        ContainerProperties containerProperties = new ContainerProperties(topic);
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, String>) records::add);
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
    }

    @AfterAll
    void tearDown() {
        Optional.ofNullable(container).ifPresent(c -> c.stop());
    }

    @SuppressWarnings("dereference.of.nullable")
    protected void assertStringPolled(String message) throws InterruptedException {
        ConsumerRecord<String, String> singleRecord = records.poll(100, TimeUnit.MILLISECONDS);
        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isNull();
        assertThat(singleRecord.value()).isEqualTo(message);
    }
}
