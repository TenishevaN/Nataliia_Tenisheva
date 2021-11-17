package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.service.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.get(login);
        return userMapper.mapUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.getAll()
                .stream()
                .map(userMapper ::mapUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = userMapper.mapUser(userDto);
        user = userRepository.add(user);
        return userMapper.mapUserDto(user);
    }

    @Override
    public UserDto updateUser(final String login, final UserDto userDto) {
        log.info("updateUser with login {}", login);
        User user = userMapper.mapUser(userDto);
        user = userRepository.update(login, user);
        return userMapper.mapUserDto(user);
    }

    @Override
    public boolean deleteUser(final String login) {
        log.info("deleteUser by login {}", login);
        return userRepository.delete(login);
    }
}