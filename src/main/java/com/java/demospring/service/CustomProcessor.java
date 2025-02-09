package com.java.demospring.service;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomProcessor implements Processor<String, String, String, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomProcessor.class);
  private ProcessorContext context;

  @Override
  public void init(ProcessorContext context) {
    this.context = context;
  }


//  @Override
//  public void process(String key, String value) {
//    if (value != null && !value.contains("error")) {
//      // Append timestamp to the message
//      String newValue = value + " (processed at " + System.currentTimeMillis() + ")";
//      LOGGER.info("Processing message: Key={}, Value={}", key, newValue);
//
//      // Forward the modified message to the next processor (or sink)
//      context.forward(key, newValue);
//    } else {
//      LOGGER.warn("Filtered out message containing 'error': {}", value);
//    }
//  }

  @Override
  public void process(Record<String, String> record) {

    if (record.value() != null & !record.value().contains("error")) {

      String newValue = record.value() + " (processed at " + System.currentTimeMillis() + ")";
      LOGGER.info("Processing message: Key={}, Value={}", record.key(), newValue);

      // Forward the modified message to the next processor (or sink)
      context.forward(new Record(record.key(), newValue, record.timestamp()));

    }

  }

  @Override
  public void close() {

  }
}
