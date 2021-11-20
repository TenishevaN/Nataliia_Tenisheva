package com.epam.spring.homework.mvc.repairAgency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String login;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ\\s]+$")
    private String name;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+[.]((com)|(org)|(net)|(ru))$")
    private String email;

}
