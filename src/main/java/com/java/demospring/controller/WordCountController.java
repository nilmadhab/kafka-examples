package com.java.demospring.controller;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
public class WordCountController {

//  private final KafkaStreams kafkaStreams;
//
//  public WordCountController(KafkaStreams kafkaStreams) {
//    this.kafkaStreams = kafkaStreams;
//  }

//  @GetMapping("/{word}")
//  public String getWordCount(@PathVariable String word) {
//
//
//    ReadOnlyKeyValueStore<String, Long> store = kafkaStreams.store(
//        StoreQueryParameters.fromNameAndType("word-count-store", QueryableStoreTypes.keyValueStore())
//    );
//
//    var count = store.get(word.toLowerCase());
//
//
//
//    return count != null ? "Word '" + word + "' has occurred " + count + " times." : "Word not found.";
//  }
}

