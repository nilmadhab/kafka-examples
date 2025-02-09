package com.java.demospring.service;

import com.java.demospring.models.Person;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  @KafkaListener(topics = "joined-results", groupId = "test-group")
  public void consume(Person person) {
    System.out.println("Consumed joined result: " + person);
  }
}
