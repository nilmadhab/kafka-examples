package com.java.demospring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  private String id;
  private String departmentId;
  private Department department;

  public static PersonBuilder toBuilder() {
    return new PersonBuilder();
  }
}