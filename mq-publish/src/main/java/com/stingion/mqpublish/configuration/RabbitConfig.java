/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.mqpublish.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${rabbitmq.exchange:#{null}}")
  private String exchange;

  @Value("${rabbitmq.queue:#{null}}")
  private String queue;

  @Bean
  public Queue ordersQueue() {
    return QueueBuilder.durable(queue).build();
  }

  @Bean
  public Exchange ordersExchange() {
    return ExchangeBuilder.directExchange(exchange).build();
  }

  @Bean
  public Binding binding(Queue ordersQueue, DirectExchange ordersExchange) {
    return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(queue);
  }
}
