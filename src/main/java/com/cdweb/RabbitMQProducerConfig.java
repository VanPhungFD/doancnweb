//package com.cdweb;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
//import org.springframework.context.annotation.Bean;
//
//@Configuration
//public class RabbitMQProducerConfig {
//
//
//	//Tạo một queue hàng đợi
//	@Value("${jsa.rabbitmq.queue}")
//	String queueName;
//
//	// tạo một topic trao đổi
//	@Value("${jsa.rabbitmq.exchange}")
//	String exchange;
//
//
//	@Value("${jsa.rabbitmq.routingkey}")
//	private String routingkey;
//
//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}
//
//	@Bean
//	DirectExchange exchange() {
//		return new DirectExchange(exchange);
//	}
///*
// * Đăng kí nhận mesage từ exchange này qua routing
// * */
//	@Bean
//	Binding binding(Queue queue, DirectExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//	}
//
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}

//	@Bean
//	public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
//			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		configurer.configure(factory, connectionFactory);
//		factory.setMessageConverter(jsonMessageConverter());
//		return factory;
//	}
//
//}
