package br.com.rbh.kafka.producer.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rbh.kafka.producer.service.MessageService;

@RestController
@RequestMapping("/kafka")
public class MessageResource {

	private final MessageService service;

	public MessageResource(MessageService service) {
		this.service = service;
	}
	
	@GetMapping
    public void sendMessageToKafkaTopic(@RequestParam String message) {
		service.sendMessage(message);
    }
}