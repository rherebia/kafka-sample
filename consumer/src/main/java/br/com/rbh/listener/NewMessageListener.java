package br.com.rbh.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewMessageListener {

	@KafkaListener(topics = "${topic.name}")
    public void listenGroupFoo(String message) {
        System.out.println(message);
    }
}
