package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;

import java.util.List;

public interface UserService {

  UserDto getUser(String login);

  List<UserDto> listUsers();

  UserDto createUser(UserDto userDto);

  UserDto updateUser(String login, UserDto userDto);

 void deleteUser(String login);

}