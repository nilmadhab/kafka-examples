package com.java.demospring.config;

import com.java.demospring.models.Department;
import com.java.demospring.models.Person;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.Stores;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.streams")
@EnableKafkaStreams
public class KafkaStreamsConfig {

  /*
  @Bean
  public Properties kafkaStreamsProperties() {
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-demo");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");


    //props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");
    //props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");

    // Set default Serdes for key-value serialization
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.LongSerde.class.getName());
    return props;
  }

  @Bean
  public StreamsBuilder streamsBuilder() {
    return new StreamsBuilder();
  }

  @Bean
  public KafkaStreams kafkaStreams(StreamsBuilder streamsBuilder, Properties properties) {
    Topology topology = streamsBuilder.build();
    KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
    kafkaStreams.start();
    return kafkaStreams;
  }
   */

  /*
  @Bean
  @Qualifier("kafkaStreamsProperties") // Explicitly name this bean
  public Properties kafkaStreamsProperties() {
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "word-count-processor-api");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$LongSerde");
    return props;
  }

  @Bean
  public StreamsBuilder streamsBuilder() {
    return new StreamsBuilder();
  }

  @Bean
  public KafkaStreams kafkaStreams(StreamsBuilder streamsBuilder,
      @Qualifier("kafkaStreamsProperties") Properties properties,
  Topology topology) {
    //Topology topology = streamsBuilder.build();
    KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
    kafkaStreams.start();
    return kafkaStreams;
  }
   */

    @Bean
    public KStream<String, Person> kafkaStream(StreamsBuilder builder) {
      // Define the departments table
      KTable<String, Department> departments = builder.table(
          "departments",
          Materialized.<String, Department>as(Stores.persistentKeyValueStore("departments"))
              .withKeySerde(Serdes.String())
              .withValueSerde(new JsonSerde<>(Department.class))
      );

      // Define the persons table
      KTable<String, Person> persons = builder.table(
          "persons",
          Materialized.<String, Person>as(Stores.persistentKeyValueStore("persons"))
              .withKeySerde(Serdes.String())
              .withValueSerde(new JsonSerde<>(Person.class))
      );

      // Perform the left join
      KTable<String, Person> joined = persons.leftJoin(
          departments,
          Person::getDepartmentId,
          (person, department) -> {
            if (department == null) {
              return Person.builder()
                  .id(person.getId()) // Ensure id is set
                  .departmentId(person.getDepartmentId()) // Ensure departmentId is set
                  .department(null)
                  .build();
            } else {
              return Person.builder()
                  .id(person.getId()) // Ensure id is set
                  .departmentId(person.getDepartmentId()) // Ensure departmentId is set
                  .department(department)
                  .build();
            }
          },
          Materialized.<String, Person>as(Stores.persistentKeyValueStore("joined-results"))
              .withKeySerde(Serdes.String())
              .withValueSerde(new JsonSerde<>(Person.class))
      );

      // Write the joined results to an output topic
      joined.toStream().to("joined-results", Produced.with(Serdes.String(), new JsonSerde<>(Person.class)));

      // Return the stream (optional, for further processing)
      return persons.toStream();
    }


}