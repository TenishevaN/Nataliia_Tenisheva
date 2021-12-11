package com.springboot.fullstack.controller;

import com.springboot.fullstack.contoller.UserController;
import com.springboot.fullstack.contoller.errorHandler.ErrorMessage;
import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.service.UserService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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

    private static UserDto userDto;

    @BeforeAll
    static void initializateValues() {

        userDto = UserDto
                .builder()
                .login("testLogin")
                .name("testName")
                .email("servicemailtest2021@gmail.com")
                .build();
    }


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

        List<UserDto> list = new ArrayList<>();
        list.add(userDto1);
        list.add(userDto2);

        when(userService.listUsers()).thenReturn(list);

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("listUsers"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(userDto1)))
                .andExpect(model().attribute("users", is(list)));
    }

    @Test
    void getAllUsersWithNullPointerExceptionTest() throws Exception {

        String message = "There is no such object";
        when(userService.listUsers()).thenThrow(new NullPointerException(message));

        mockMvc.perform(get("/user"))
                .andExpect(status().isBadRequest())
                .andDo(result -> {
                    Map<String, Object> model = result.getModelAndView().getModel();
                    ErrorMessage errorMessage = (ErrorMessage) model.get("errorMessage");
                    assertThat(errorMessage.getMessage(), is(equalTo(message)));
                });
    }

    @Test
    void getUser() throws Exception {

        given(userService.getUser(any(String.class))).willReturn(userDto);

        mockMvc.perform(get("/user/{login}", userDto.getLogin())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDto))
                        .sessionAttr("user", userDto)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("cardUser"))
                .andDo(result -> {
                    Map<String, Object> model = result.getModelAndView().getModel();
                    UserDto createdUser = (UserDto) model.get("user");
                    assertThat(createdUser.getName(), is(equalTo("testName")));
                    assertThat(createdUser.getLogin(), is(equalTo("testLogin")));
                });
    }


    @Test
    void getUserEntityNotFoundExceptionTest() throws Exception {

        String message = "Object is not found";
        when(userService.getUser(any(String.class))).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/user/{login}", "login"))
                .andExpect(status().isNotFound())
                .andDo(result -> {
                    Map<String, Object> model = result.getModelAndView().getModel();
                    ErrorMessage errorMessage = (ErrorMessage) model.get("errorMessage");
                    assertThat(errorMessage.getMessage(), is(equalTo(message)));
                });
    }

    @Test
    void createUserTest() throws Exception {

        given(userService.createUser(any(UserDto.class), any(String.class))).willReturn(userDto);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDto))
                        .sessionAttr("user", userDto)
                        .param("password", "1")
                                      )
                .andExpect(status().isCreated())
                .andExpect(view().name("mainPage"))
                .andDo(result -> {
                    Map<String, Object> model = result.getModelAndView().getModel();
                    UserDto createdUser = (UserDto) model.get("user");
                    assertThat(createdUser.getName(), is(equalTo("testName")));
                    assertThat(createdUser.getLogin(), is(equalTo("testLogin")));
                    assertThat(createdUser.getEmail(), is(equalTo("servicemailtest2021@gmail.com")));
                });
    }

    @Test
    void updateUser() throws Exception {

        given(userService.updateUser(any(UserDto.class))).willReturn(userDto);

        mockMvc.perform(post("/user/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDto))
                        .sessionAttr("user", userDto)

                )
                .andExpect(status().isOk())
                .andExpect(view().name("cardUser"))
                .andDo(result -> {
                    Map<String, Object> model = result.getModelAndView().getModel();
                    UserDto updatedUser = (UserDto) model.get("user");
                    assertThat(updatedUser.getName(), is(equalTo("testName")));
                    assertThat(updatedUser.getLogin(), is(equalTo("testLogin")));
                    assertThat(updatedUser.getEmail(), is(equalTo("servicemailtest2021@gmail.com")));
                });
    }

    @Test
    void deleteUser() throws Exception {

        doNothing().when(userService).deleteUser(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/delete/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void validateThatLoginDoesNotMatchRegexTest() throws Exception {

        UserDto  userDtoLogin = (UserDto) userDto.clone();
        userDtoLogin.setLogin("Login#");

        given(userService.createUser(any(UserDto.class), any(String.class))).willReturn(userDtoLogin);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDtoLogin))
                        .sessionAttr("user", userDtoLogin)
                )
                .andExpect(view().name("error"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void validateThatNameDoesNotMatchRegexTest() throws Exception {

        UserDto  userDtoName = (UserDto) userDto.clone();
        userDtoName.setName("testName1#");

        given(userService.createUser(any(UserDto.class), any(String.class))).willReturn(userDtoName);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDtoName))
                        .sessionAttr("user", userDtoName)
                )
                .andExpect(view().name("error"))
                .andExpect(status().isNotAcceptable());
           }

    @Test
    void validateThatEmailDoesNotMatchRegexTest() throws Exception {

        UserDto  userDtoEmail = (UserDto) userDto.clone();
        userDtoEmail.setEmail("test@gmail");

        given(userService.createUser(any(UserDto.class), any(String.class))).willReturn(userDtoEmail);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDtoEmail))
                        .sessionAttr("user", userDtoEmail)
                )
                .andExpect(view().name("error"))
                .andExpect(status().isNotAcceptable());
     }

    @Test
    void validateNotBlankFieldTest() throws Exception {

        UserDto  userDtoNullValue = (UserDto) userDto.clone();
        userDtoNullValue.setName(null);

        given(userService.createUser(any(UserDto.class), any(String.class))).willReturn(userDtoNullValue);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(IntegrationTestUtil.convertObjectToFormUrlEncodedBytes(userDtoNullValue))
                        .sessionAttr("user", userDtoNullValue)
                )
                .andExpect(view().name("error"))
                .andExpect(status().isNotAcceptable());
      }
}
