package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUser(String login);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto, String password);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String id);

    UserDto updateName(String name, Long id);

}