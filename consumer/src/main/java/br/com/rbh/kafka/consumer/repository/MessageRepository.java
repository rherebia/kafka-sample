package br.com.rbh.kafka.consumer.repository;

import java.util.List;

public interface MessageRepository {

	void save(String message);
	
	List<String> getAll();
}
