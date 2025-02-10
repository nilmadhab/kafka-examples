package com.java.demospring.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamUtils;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaStreamProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamProcessor.class);

  @Bean
  public KStream<String, String> kStream(StreamsBuilder builder) {

    KStream<String, String> inputStream = builder.stream("input-topic",  Consumed.with(Serdes.String(), Serdes.String()));

    KStream<String, String> processedStream = KStreamUtils.nilMapValues(inputStream);

    //KStream<String, String> processedStream = inputStream.mapValues(s -> s.toUpperCase());

    processedStream.foreach((k, v) -> LOGGER.info("Processed Message: " + v));

    processedStream.to("output-topic", Produced.with(Serdes.String(), Serdes.String()));

    return inputStream;

  }





}
