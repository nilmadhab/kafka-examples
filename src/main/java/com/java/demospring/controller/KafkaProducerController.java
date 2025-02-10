package com.java.demospring.controller;

import com.java.demospring.models.Department1;
import com.java.demospring.models.Person1;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

  private final KafkaTemplate<String, Person1> personKafkaTemplate;
  private final KafkaTemplate<String, Department1> departmentKafkaTemplate;

  public KafkaProducerController(KafkaTemplate<String, Person1> personKafkaTemplate,
      KafkaTemplate<String, Department1> departmentKafkaTemplate) {
    this.personKafkaTemplate = personKafkaTemplate;
    this.departmentKafkaTemplate = departmentKafkaTemplate;
  }

  @PostMapping("/produce/person")
  public String producePerson(@RequestBody Person1 person) {
    personKafkaTemplate.send("persons", person.getId(), person);
    return "Person produced: " + person;
  }

  @PostMapping("/produce/department")
  public String produceDepartment(@RequestBody Department1 department) {
    departmentKafkaTemplate.send("departments", department.getId(), department);
    return "Department produced: " + department;
  }
}