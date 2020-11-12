package br.com.rbh.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Value(value = "${topic.name}")
    private String topic;

	public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	public void sendMessage(String message) {
		LOGGER.info(String.format("Sending message: %s", message));

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic, message);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				LOGGER.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOGGER.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
			}
		});
	}
}
