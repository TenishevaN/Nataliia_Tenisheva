package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = RepairRequestController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)

public class RepairRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairRequestService repairRequestService;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void validateNotBlankDescriptionFieldTest() throws Exception {

        RepairRequestDto repairRequestDto = RepairRequestDto
                .builder()
                .description("")
                .build();

        given(repairRequestService.add(any( RepairRequestDto.class))).willReturn(repairRequestDto);

        mockMvc.perform(post("/repair-request")
                        .content(mapper.writeValueAsString(repairRequestDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
