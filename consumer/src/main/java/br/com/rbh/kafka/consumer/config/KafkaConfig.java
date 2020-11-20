package br.com.rbh.kafka.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Value(value = "${topic.name}")
    private String topicName;

    @Bean
    public NewTopic topic() {
      return TopicBuilder.name(topicName).build();
    }
}