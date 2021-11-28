package com.epam.spring.homework.mvc.repairAgency.controller;
import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    @Test
    void validateAmmountFieldTest() throws Exception {

        InvoiceBalanceDto invoiceBalanceDto = InvoiceBalanceDto
                .builder()
                .ammount(BigDecimal.valueOf(1.5555555))
                .build();

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

        InvoiceBalanceDto invoiceBalanceDto = InvoiceBalanceDto
                .builder()
                .build();

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
