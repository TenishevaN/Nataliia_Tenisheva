package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    public List<UserDto> getAllUsers() {
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{login}")
    public UserDto getUser(@PathVariable String login) {

        UserDto findedUser = userService.getUser(login);
        if (findedUser == null) {
            throw new NullPointerException();
        }
        return findedUser;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {

        UserDto newUser = userService.createUser(userDto);
        if (newUser == null) {
            throw new NullPointerException();
        }
        return newUser;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{login}")
    public UserDto updateUser(@PathVariable String login, @Valid @RequestBody UserDto userDto) {

        UserDto findedUser = userService.updateUser(login, userDto);
        if (findedUser == null) {
            throw new NullPointerException();
        }
        return findedUser;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/{login}")
    public void deleteUser(@PathVariable String login) {

        try {
            userService.deleteUser(login);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();
        }
    }
}