package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.mapper.UserMapper;
import com.springboot.fullstack.model.User;
import com.springboot.fullstack.repository.UserRepository;
import com.springboot.fullstack.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final String MOCK_LOGIN = "userOne";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void getUserByLoginTest() {

        //given
        User expectedUser = new User(MOCK_LOGIN);
        when(userRepository.findByLogin(MOCK_LOGIN)).thenReturn(expectedUser);

        //when
        UserDto actualUser = userService.getUser(MOCK_LOGIN);

        //then
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
    }

    @Test
    void getListUsers() {

        User testUser1 = new User(MOCK_LOGIN);
        User testUser2 = new User(MOCK_LOGIN);

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser1, testUser2));

        List<UserDto> data = userService.listUsers();

        assertEquals(2, data.size());
    }

    @Test
    void createUser() {

        //given
        User expectedUser = new User(MOCK_LOGIN);
        expectedUser.setName(MOCK_LOGIN);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        //when
        UserDto actualUser = userService.createUser(userMapper.mapUserDto(expectedUser));

        //then
        assertEquals(expectedUser.getName(), actualUser.getName());
    }

    @Test
    void updateUser() {

        //given
        User expectedUser = new User(MOCK_LOGIN);
        expectedUser.setName(MOCK_LOGIN);
        expectedUser.setRoleId(1);

        UserDto updatedUser = userMapper.mapUserDto(expectedUser);
        updatedUser.setEmail("testEmail@gmail.com");

        when(userRepository.findByLogin(MOCK_LOGIN)).thenReturn(expectedUser);

        //when
        UserDto actualUser = userService.updateUser(updatedUser);

        //then
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    void deleteUserTest() {

        //given
        doNothing().when(userRepository).deleteById(1L);

        //when
        userService.deleteUser("1");
        userService.deleteUser("1");

        //then
        verify(userRepository, times(2)).deleteById(1L);
    }

    @Test
    void updateNameTest() {

        //given
        User expectedUser = new User(MOCK_LOGIN);
        expectedUser.setName(MOCK_LOGIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        //when
        UserDto actualUser = userService.updateName("updatedName", 1L);

        //then
        assertEquals(expectedUser.getName(), actualUser.getName());
    }
}
