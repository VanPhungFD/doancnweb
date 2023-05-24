package com.cdweb.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cdweb.model.OrderModel;
@Component
public class ProducerRabbitMQ {

//	@Autowired
//	private AmqpTemplate amqpTemplate;
//
//	@Value("${jsa.rabbitmq.exchange}")
//	private String exchange;
//
//	@Value("${jsa.rabbitmq.routingkey}")
//	private String routingkey;
//
//
//	public void sendData(OrderModel order) {
//		amqpTemplate.convertAndSend(exchange, routingkey, order);
//	}
	
}
