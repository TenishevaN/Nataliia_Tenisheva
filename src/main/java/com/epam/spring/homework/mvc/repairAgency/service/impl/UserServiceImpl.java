package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.service.model.User;
import com.epam.spring.homework.mvc.repairAgency.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.get(login);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.getAll()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.add(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(final String login, final UserDto userDto) {
        log.info("updateUser with login {}", login);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.update(login, user);
        return mapUserToUserDto(user);
    }

    @Override
    public boolean deleteUser(final String login) {
        log.info("deleteUser by login {}", login);
        return userRepository.delete(login);
    }

    private UserDto mapUserToUserDto(final User user) {
        return UserDto.builder()
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }

    private User mapUserDtoToUser(final UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .build();
    }

}