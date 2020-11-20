package br.com.rbh.kafka.consumer.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import br.com.rbh.kafka.consumer.listener.NewMessageListener;
import br.com.rbh.kafka.consumer.repository.MessageRepository;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NewMessageListenerTests {
	
	@Autowired
	private EmbeddedKafkaBroker broker;
	
	@SpyBean
	private NewMessageListener newMessageListener;
	
	@Value("${topic.name}")
    private String topic;
	
	@Autowired
	private MessageRepository messageRepository;
	
	private Producer<String, String> producer;
	
	@Captor
    ArgumentCaptor<String> messageArgumentCaptor;
	
	@BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(broker));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer()).createProducer();
    }
    
    @AfterAll
    void shutdown() {
        producer.close();
    }
	
    @Test
    public void mustCallKafkaListener() throws Exception {
    	String message = "Hello";
    	
    	List<String> messagesBeforeEvent = messageRepository.getAll();
    	
        producer.send(new ProducerRecord<>(topic, 0, UUID.randomUUID().toString(), message));
        producer.flush();

        verify(newMessageListener, timeout(5000).times(1))
        	.listenGroupFoo(messageArgumentCaptor.capture());
        
        Thread.sleep(5000);
        
        List<String> messagesAfterEvent = messageRepository.getAll();
        
        assertThat(messageArgumentCaptor.getValue()).isEqualTo(message);
        assertThat(messagesAfterEvent.size()).isEqualTo(messagesBeforeEvent.size() + 1);
    }

}
