package com.java.demospring.service;

import java.util.Arrays;
import org.apache.kafka.streams.kstream.KStreamUtils;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCountProcessor implements Processor<String, String, Void, Void> {

  private static final Logger LOGGER = LoggerFactory.getLogger(WordCountProcessor.class);
  private ProcessorContext<Void, Void> context;
  private KeyValueStore<String, Long> wordCountStore;

  @Override
  public void init(ProcessorContext<Void, Void> context) {
    this.context = context;
    this.wordCountStore = context.getStateStore("word-count-store");
  }

  /*
  String text = record.value();
        if (text != null) {
            Arrays.stream(text.toLowerCase().split("\\W+"))  // Tokenize words
                .forEach(word -> {
                    Long oldCount = wordCountStore.get(word);
                    Long newCount = (oldCount == null) ? 1 : oldCount + 1;

                    wordCountStore.put(word, newCount);
                    LOGGER.info("Updated count: '{}' = {}", word, newCount);
                });
        }
   */

  @Override
  public void process(Record<String, String> record) {
    //KStreamUtils.nilMapValues()
    String text = record.value();
    if (text != null) {
      Arrays.stream(text.toLowerCase().split("\\W+"))
          .forEach(word -> {
            Long oldCount = wordCountStore.get(word);
            Long newCount = (oldCount == null) ? 1 : oldCount + 1;
            wordCountStore.put(word, newCount);
            LOGGER.info("Updated count: '{}' = {}", word, newCount);
          });
    }

  }

  @Override
  public void close() {
    Processor.super.close();
  }
}
