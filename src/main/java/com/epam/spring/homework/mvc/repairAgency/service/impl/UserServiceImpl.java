package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.mapper.UserMapper;
import com.epam.spring.homework.mvc.repairAgency.model.User;
import com.epam.spring.homework.mvc.repairAgency.repository.UserRepository;
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
        User user = userRepository.findByLogin(login);
        return userMapper.mapUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper ::mapUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = userMapper.mapUser(userDto);
        user = userRepository.save(user);
        return userMapper.mapUserDto(user);
    }

    @Override
    public UserDto updateUser(final String login, final UserDto userDto) {
        log.info("updateUser with login {}", login);
        User user = userMapper.mapUser(userDto);
        user = userRepository.update(user.getName(), login);
        return userMapper.mapUserDto(user);
    }

    @Override
    public void deleteUser(final String login) {
        log.info("deleteUser by login {}", login);
        UserDto userDto = getUser(login);
        User user = userMapper.mapUser(userDto);
        userRepository.delete(user);
    }
}