package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = InvoiceBalanceController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class InvoiceBalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceBalanceService invoiceBalanceService;

    private final ObjectMapper mapper = new ObjectMapper();
    private static InvoiceBalanceDto invoiceBalanceDto;
    private static InvoiceBalanceDto invoiceBalanceWithAmmountDto;

    @BeforeAll
    static void initializateValues() {

        invoiceBalanceDto = InvoiceBalanceDto
                .builder()
                .build();

        invoiceBalanceWithAmmountDto = InvoiceBalanceDto
                .builder()
                .ammount(BigDecimal.valueOf(200))
                .build();
    }

    @Test
    void addInvoiceBalanceTest() throws Exception {

        given(invoiceBalanceService.add(any(InvoiceBalanceDto.class))).willReturn(invoiceBalanceWithAmmountDto);

        mockMvc.perform(post("/invoice-balance")
                        .content(mapper.writeValueAsString(invoiceBalanceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateInvoiceBalanceTest() throws Exception {

        given(invoiceBalanceService.update(anyLong(), any(InvoiceBalanceDto.class))).willReturn(invoiceBalanceWithAmmountDto);

        mockMvc.perform(put("/invoice-balance/{id}", 1L)
                        .content(mapper.writeValueAsString(invoiceBalanceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateInvoiceBalanceAmmountTest() throws Exception {

        invoiceBalanceDto.setAmmount(BigDecimal.valueOf(200));
        given(invoiceBalanceService.updateAmmount(anyLong(), any(BigDecimal.class))).willReturn(invoiceBalanceWithAmmountDto);

        mockMvc.perform(patch("/invoice-balance/{id}/{ammount}", 1L, BigDecimal.valueOf(200)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ammount").value(invoiceBalanceWithAmmountDto.getAmmount()));
    }

    @Test
    void getAllInvoiceBalancesTest() throws Exception {

        when(invoiceBalanceService.getAll()).thenReturn(Arrays.asList(invoiceBalanceWithAmmountDto));

        mockMvc.perform(get("/invoice-balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].ammount").value(invoiceBalanceWithAmmountDto.getAmmount()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getInvoiceBalanceTest() throws Exception {

        given(invoiceBalanceService.get(anyLong())).willReturn(invoiceBalanceWithAmmountDto);

        mockMvc.perform(get("/invoice-balance/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.ammount").value(invoiceBalanceWithAmmountDto.getAmmount()));
    }

    @Test
    void validateAmmountFieldTest() throws Exception {

        invoiceBalanceDto.setAmmount(BigDecimal.valueOf(1.5555555));
        given(invoiceBalanceService.add(any(InvoiceBalanceDto.class))).willReturn(invoiceBalanceDto);

        mockMvc.perform(post("/invoice-balance")
                        .content(mapper.writeValueAsString(invoiceBalanceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void validateAmmountFieldNotNullTest() throws Exception {

        given(invoiceBalanceService.add(any(InvoiceBalanceDto.class))).willReturn(invoiceBalanceDto);

        mockMvc.perform(post("/invoice-balance")
                        .content(mapper.writeValueAsString(invoiceBalanceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
