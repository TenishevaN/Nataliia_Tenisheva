package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.AccountLocalizationDto;
import com.epam.spring.homework.mvc.repairAgency.service.AccountLocalizationService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = AccountLocalizationController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class AccountLocalizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountLocalizationService accountLocalizationService;
    private static AccountLocalizationDto accountLocalizationDto;

    @BeforeAll
    static void initializateValues() {

        accountLocalizationDto = AccountLocalizationDto
                .builder()
                .accountId(1L)
                .languageId(1L)
                .name("testName")
                .build();
    }

    @Test
    void getAllByUserIdTest() throws Exception {

        given(accountLocalizationService.getAllByUserId(1L)).willReturn(Arrays.asList(accountLocalizationDto));

        mockMvc.perform(get("/account-localization/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value(accountLocalizationDto.getName()));
    }

    @Test
    void getByUserIdLanguageId() throws Exception {

        given(accountLocalizationService.get(1L, 1L)).willReturn(accountLocalizationDto);

        mockMvc.perform(get("/account-localization/{userId}/{languageId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(accountLocalizationDto.getName()));
    }

    @Test
    void updateName() throws Exception {

        given(accountLocalizationService.update(anyLong(), anyLong(), any(String.class))).willReturn(1);

        mockMvc.perform(patch("/account-localization/{userId}/{languageId}/{name}", 1L, 1L, "updateName"))
                .andExpect(status().isOk());
    }
}
