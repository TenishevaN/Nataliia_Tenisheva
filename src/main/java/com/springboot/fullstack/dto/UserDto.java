package com.springboot.fullstack.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserDto implements Cloneable{

    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String login;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ\\s]+$")
    private String name;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+[.]((com)|(org)|(net)|(ru))$")
    private String email;

    private Integer roleId;
    private Integer invoiceId;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
