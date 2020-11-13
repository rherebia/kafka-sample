package br.com.rbh.kafka.producer.handler;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.rbh.kafka.producer.service.MessageService;
import reactor.core.publisher.Mono;

@Component
public class MessageHandler {

	private final MessageService service;

	public MessageHandler(MessageService service) {
		this.service = service;
	}
	
    public Mono<ServerResponse> sendMessageToKafkaTopic(ServerRequest request) {
    	String message = request.queryParam("message").orElse("Default message");
    	
		service.sendMessage(message);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new JSONObject().put("msg", "Message sent!").toString());
    }
}
