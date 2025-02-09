package com.java.demospring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.Properties;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
//@EnableKafkaStreams
public class DemoSpringApplication {

  /*

  private KafkaStreams kafkaStreams;

  public DemoSpringApplication(Topology topology) {
    Properties props = new Properties();
    props.put("application.id", "kafka-streams-processor-api");
    props.put("bootstrap.servers", "localhost:9092");

    this.kafkaStreams = new KafkaStreams(topology, props);
  }

  @PostConstruct
  public void startStreams() {
    kafkaStreams.start();
  }

  @PreDestroy
  public void stopStreams() {
    kafkaStreams.close();
  }
   */

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringApplication.class, args);
  }

}
