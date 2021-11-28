package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.StatusDto;
import com.epam.spring.homework.mvc.repairAgency.service.StatusService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = StatusController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @Test
    void getStatusTest() throws Exception {

        StatusDto statusDto = StatusDto
                .builder()
                .name("new")
                .build();

        given(statusService.get(any(Long.class), any(String.class))).willReturn(statusDto);

        mockMvc.perform(get("/status/{locale}/{id}", statusDto.getName(), 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(statusDto.getName()));
    }

    @Test
    void getAllStatusesTest() throws Exception {

        StatusDto statusDtoNew = StatusDto
                .builder()
                .name("новый")
                .build();
        StatusDto statusDtoClosed = StatusDto
                .builder()
                .name("закрыт")
                .build();

        when(statusService.getAll(any(String.class))).thenReturn(Arrays.asList(statusDtoNew, statusDtoClosed));

        mockMvc.perform(get("/status/{locale}", "ru"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value(statusDtoNew.getName()))
                .andExpect(jsonPath("$[1].name").value(statusDtoClosed.getName()))
                .andExpect(jsonPath("$").isArray());
    }
}
