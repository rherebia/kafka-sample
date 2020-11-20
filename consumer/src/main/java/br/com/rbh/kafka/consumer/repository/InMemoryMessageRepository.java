package br.com.rbh.kafka.consumer.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class InMemoryMessageRepository implements MessageRepository {
	
	private final List<String> messages = new ArrayList<>();

	@Override
	public void save(String message) {
		messages.add(message);
	}
	
	@Override
	public List<String> getAll() {
		return new ArrayList<>(messages);
	}
}
