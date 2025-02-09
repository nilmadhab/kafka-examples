package com.java.demospring.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//@Configuration
public class KafkaConfig {

//  @Bean
//  public static Properties getStreamsConfig() {
//    Properties props = new Properties();
//    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-demo");
//    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//    return props;
//  }
}

