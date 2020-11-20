package br.com.rbh.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.rbh.kafka.consumer.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewMessageListener {
	
	private final MessageRepository repository;
	
	@KafkaListener(topics = "${topic.name}")
    public void listenGroupFoo(String message) {
        System.out.println(message);
        
        repository.save(message);
    }
}
