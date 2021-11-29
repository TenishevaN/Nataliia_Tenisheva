package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.UserDto;
import com.epam.spring.homework.mvc.repairAgency.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void getAllUsers() throws Exception {

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
                .andExpect(content().contentType("application/json;charset=UTF-8"))
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

    @Test
    void getUser() throws Exception {

        UserDto userDto = UserDto
                .builder()
                .login("testLogin")
                .name("testName")
                .build();

        given(userService.getUser(any(String.class))).willReturn(userDto);

        mockMvc.perform(get("/user/{login}", userDto.getLogin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(userDto.getName()));
    }

    @Test
    void createUserTest() throws Exception {

        UserDto userDto = UserDto
                .builder()
                .login("testLogin")
                .name("testName")
                .build();

        given(userService.createUser(any(UserDto.class))).willReturn(userDto);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(userDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateUser() throws Exception {

        UserDto userDto = UserDto
                .builder()
                .login("testLogin")
                .name("testName")
                .build();

        given(userService.updateUser(any(UserDto.class))).willReturn(userDto);

        mockMvc.perform(put("/user")
                        .content(mapper.writeValueAsString(userDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateUserName() throws Exception {

        UserDto userDto = UserDto
                .builder()
                .id(1L)
                .login("testLogin")
                .name("testName")
                .build();

        given(userService.updateName(any(String.class), anyLong())).willReturn(userDto);

        mockMvc.perform(patch("/user/{id}/{name}", userDto.getId(), userDto.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userDto.getName()));
    }

    @Test
    void deleteUser() throws Exception {

        doNothing().when(userService).deleteUser(any(String.class));

        mockMvc.perform(delete("/user/{id}", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void validateThatLoginDoesNotMatchRegexTest() throws Exception {

        UserDto userDto1 = UserDto
                .builder()
                .login("adminA#")
                .name("adminA")
                .build();

        given(userService.createUser(any(UserDto.class))).willReturn(userDto1);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void validateThatNameDoesNotMatchRegexTest() throws Exception {

        UserDto userDto1 = UserDto
                .builder()
                .login("adminA")
                .name("adminA1#")
                .build();

        given(userService.createUser(any(UserDto.class))).willReturn(userDto1);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void validateThatEmailDoesNotMatchRegexTest() throws Exception {

        UserDto userDto1 = UserDto
                .builder()
                .login("adminA")
                .name("adminA")
                .email("test@gmail")
                .build();

        given(userService.createUser(any(UserDto.class))).willReturn(userDto1);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void validateNotBlankFieldTest() throws Exception {

        UserDto userDto1 = UserDto
                .builder()
                .name("adminA")
                .build();

        given(userService.createUser(any(UserDto.class))).willReturn(userDto1);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
