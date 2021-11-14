package com.epam.spring.homework.mvc.repairAgency.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private int id;
  private String login;
  private String name;
  private String email;
  private transient String password;
  private int roleId;
  private int invoiceId;
}