package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = InvoiceController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    private final ObjectMapper mapper = new ObjectMapper();

    private static InvoiceDto invoiceDto;
    private static InvoiceDto invoiceWithAmmountDto;

    @BeforeAll
    static void initializateValues() {

        invoiceDto = InvoiceDto
                .builder()
                .build();

        invoiceWithAmmountDto = InvoiceDto
                .builder()
                .ammount(BigDecimal.valueOf(200))
                .build();
    }

    @Test
    void addInvoiceTest() throws Exception {

        given(invoiceService.add(any(InvoiceDto.class))).willReturn(invoiceWithAmmountDto);

        mockMvc.perform(post("/invoice")
                        .content(mapper.writeValueAsString(invoiceWithAmmountDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void updateInvoiceTest() throws Exception {

        given(invoiceService.update(anyLong(), any(InvoiceDto.class))).willReturn(invoiceWithAmmountDto);

        mockMvc.perform(put("/invoice/{id}", 1L)
                        .content(mapper.writeValueAsString(invoiceWithAmmountDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


    @Test
    void getAllInvoicesTest() throws Exception {

        when(invoiceService.getAll()).thenReturn(Arrays.asList(invoiceWithAmmountDto));

        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].ammount").value(invoiceWithAmmountDto.getAmmount()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getInvoiceTest() throws Exception {

        given(invoiceService.get(anyLong())).willReturn(invoiceWithAmmountDto);

        mockMvc.perform(get("/invoice/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.ammount").value(invoiceWithAmmountDto.getAmmount()));
    }

    @Test
    void deleteInvoiceTest() throws Exception {


        when(invoiceService.delete(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/invoice/{id}", 1L))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


    @Test
    void validateAmmountFieldTest() throws Exception {

        InvoiceDto invoiceDto = InvoiceDto
                .builder()
                .ammount(BigDecimal.valueOf(1.5555555))
                .build();

        given(invoiceService.add(any(InvoiceDto.class))).willReturn(invoiceDto);

        mockMvc.perform(post("/invoice")
                        .content(mapper.writeValueAsString(invoiceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void validateAmmountFieldNotNullTest() throws Exception {

        given(invoiceService.add(any(InvoiceDto.class))).willReturn(invoiceDto);

        mockMvc.perform(post("/invoice")
                        .content(mapper.writeValueAsString(invoiceDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
