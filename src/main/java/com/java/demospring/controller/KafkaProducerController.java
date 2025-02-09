package com.java.demospring.controller;

import com.java.demospring.models.Department;
import com.java.demospring.models.Person;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

  private final KafkaTemplate<String, Person> personKafkaTemplate;
  private final KafkaTemplate<String, Department> departmentKafkaTemplate;

  public KafkaProducerController(KafkaTemplate<String, Person> personKafkaTemplate,
      KafkaTemplate<String, Department> departmentKafkaTemplate) {
    this.personKafkaTemplate = personKafkaTemplate;
    this.departmentKafkaTemplate = departmentKafkaTemplate;
  }

  @PostMapping("/produce/person")
  public String producePerson(@RequestBody Person person) {
    personKafkaTemplate.send("persons", person.getId(), person);
    return "Person produced: " + person;
  }

  @PostMapping("/produce/department")
  public String produceDepartment(@RequestBody Department department) {
    departmentKafkaTemplate.send("departments", department.getId(), department);
    return "Department produced: " + department;
  }
}