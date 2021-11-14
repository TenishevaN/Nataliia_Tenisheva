package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private String login;
  private String name;
  private String email;

}
