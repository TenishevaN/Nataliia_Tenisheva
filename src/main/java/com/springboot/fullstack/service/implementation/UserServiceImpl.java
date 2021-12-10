package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.mapper.UserMapper;
import com.springboot.fullstack.model.User;
import com.springboot.fullstack.repository.UserRepository;
import com.springboot.fullstack.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(userMapper::mapUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("createUser with name {}", userDto.getName());
        User user = userMapper.mapUser(userDto);
        user.setRoleId(1);
        user.setPassword("4564");
        user = userRepository.save(user);
        log.info("createdUser  {}", user);
        return userMapper.mapUserDto(user);
    }

    @Override
    public UserDto updateUser(final UserDto userDto) {

        log.info("userDto {}", userDto);
        User user = userRepository.findByLogin(userDto.getLogin());
        log.info("user {}", user);
        user.setName(userDto.getName());
        user.setRoleId(userDto.getRoleId());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        log.info("userUpdated {}", user);
        return userMapper.mapUserDto(user);
    }

    @Override
    public void deleteUser(final String id) {
        log.info("deleteUser by id {}", id);
        userRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public UserDto updateName(String name, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            userRepository.save(user);
            log.info("userUpdated {}", user);
            return userMapper.mapUserDto(user);
        }
        return null;
    }
}