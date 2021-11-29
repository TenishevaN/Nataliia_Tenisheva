package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(value = RepairRequestController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)

public class RepairRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairRequestService repairRequestService;

    private final ObjectMapper mapper = new ObjectMapper();
    private static RepairRequestDto repairRequestDto;
    private static RepairRequestDto repairRequestWithDscriptionDto;

    @BeforeAll
    static void initializateValues() {

        repairRequestDto = RepairRequestDto
                .builder()
                .build();

        repairRequestWithDscriptionDto = RepairRequestDto
                .builder()
                .description("test description")
                .build();
    }

    @Test
    void addRepairRequestTest() throws Exception {

        given(repairRequestService.add(any(RepairRequestDto.class))).willReturn(repairRequestWithDscriptionDto);

        mockMvc.perform(post("/repair-request")
                        .content(mapper.writeValueAsString(repairRequestWithDscriptionDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateRepairRequestTest() throws Exception {

        given(repairRequestService.update(anyLong(), any(RepairRequestDto.class))).willReturn(repairRequestWithDscriptionDto);

        mockMvc.perform(put("/repair-request/{id}", 1L)
                        .content(mapper.writeValueAsString(repairRequestWithDscriptionDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void getAllPaginatedTest() throws Exception {

        given(repairRequestService.getAllSortedAndPaginated(1)).willReturn(Arrays.asList(repairRequestWithDscriptionDto));

        mockMvc.perform(get("/repair-request/page/{page}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].description").value(repairRequestWithDscriptionDto.getDescription()));
    }

    @Test
    void getAllRepairRequestsTest() throws Exception {

        when(repairRequestService.getAll()).thenReturn(Arrays.asList(repairRequestWithDscriptionDto));

        mockMvc.perform(get("/repair-request"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].description").value(repairRequestWithDscriptionDto.getDescription()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getRepairRequestTest() throws Exception {

        given(repairRequestService.get(anyLong())).willReturn(repairRequestWithDscriptionDto);

        mockMvc.perform(get("/repair-request/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.description").value(repairRequestWithDscriptionDto.getDescription()));
    }

    @Test
    void deleteRepairRequestTest() throws Exception {

        when(repairRequestService.delete(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/repair-request/{id}", 1L))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


    @Test
    void validateNotBlankDescriptionFieldTest() throws Exception {

        RepairRequestDto repairRequestDto = RepairRequestDto
                .builder()
                .description("")
                .build();

        given(repairRequestService.add(any(RepairRequestDto.class))).willReturn(repairRequestDto);

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
