package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Supplier<Flux<Message<String>>> produceMessage() {
		return () -> Flux.interval(Duration.ofSeconds(30))
				.map(tick -> {
					String ID = String.valueOf(UUID.randomUUID());
					String messageContent = "Message: " + ID;
					Message<String> message = MessageBuilder.withPayload(messageContent).build();
					System.out.println("producing message with ID: "+ID);
					return message;
				});
	}

}
