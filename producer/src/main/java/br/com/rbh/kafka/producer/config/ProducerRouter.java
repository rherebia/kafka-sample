package br.com.rbh.kafka.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.rbh.kafka.producer.handler.MessageHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ProducerRouter {

	@Bean
	public RouterFunction<ServerResponse> productsRoute(MessageHandler messageHandler){
		return RouterFunctions
				.route(GET("/kafka").and(accept(MediaType.APPLICATION_JSON)), 
						messageHandler::sendMessageToKafkaTopic);
	}
}
