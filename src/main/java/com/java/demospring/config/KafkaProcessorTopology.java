package com.java.demospring.config;


import com.java.demospring.service.CustomProcessor;
import com.java.demospring.service.WordCountProcessor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProcessorTopology {

  @Bean
  public Topology createTopology() {
    StreamsBuilder builder = new StreamsBuilder();
    Topology topology = builder.build();

    /*
    topology.addSource("SourceProcessor", Serdes.String().deserializer(), Serdes.String().deserializer(),  "input-topic")
        .addProcessor("CustomProcessor", CustomProcessor::new, "SourceProcessor" )
        .addSink("SinkProcessor",  "output-topic", Serdes.String().serializer(), Serdes.String().serializer(),"CustomProcessor");
    */

    /*
     // Define a persistent state store
        StoreBuilder<?> storeBuilder = Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore("word-count-store"),
                Serdes.String(),
                Serdes.Long()
        );

        topology.addSource("SourceProcessor", "input-topic")
                .addProcessor("WordCountProcessor", WordCountProcessor::new, "SourceProcessor")
                .addStateStore(storeBuilder, "WordCountProcessor");

     */

    StoreBuilder<?> storeBuilder = Stores.keyValueStoreBuilder(
        Stores.persistentKeyValueStore("word-count-store"),
        Serdes.String(),
        Serdes.Long()
    );

    topology.addSource("SourceProcessor", Serdes.String().deserializer(), Serdes.String().deserializer(),  "input-topic")
        //.addProcessor("CustomProcessor", CustomProcessor::new, "SourceProcessor" )
        .addProcessor("WordCountProcessor", WordCountProcessor::new, "SourceProcessor" )
        .addStateStore(storeBuilder, "WordCountProcessor");
        //.addSink("SinkProcessor",  "output-topic", Serdes.String().serializer(), Serdes.String().serializer(),"WordCountProcessor");

    return topology;

  }
}
