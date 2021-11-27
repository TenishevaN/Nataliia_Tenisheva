package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.model.User;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers() throws Exception {

        User testUser1 = new User("userOne");
        testUser1.setId(1L);
        testUser1.setName("userOne");
        testUser1.setEmail("test@gmail.com");

        UserDto userDto1 = UserDto
                .builder()
                .login("userFirst")
                .build();

        UserDto userDto2 = UserDto
                .builder()
                .login("userSecond")
                .build();

        when(userService.listUsers()).thenReturn(Arrays.asList(userDto1, userDto2));

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].login").value(userDto1.getLogin()))
                .andExpect(jsonPath("$[1].login").value(userDto2.getLogin()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAllUsersWithExceptionTest() throws Exception {

        String message = "Null pointer exception";


        when(userService.listUsers()).thenThrow(new NullPointerException(message));

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(message));

    }


}
