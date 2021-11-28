package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.InvoiceBalanceMapper;
import com.epam.spring.homework.mvc.repairAgency.model.InvoiceBalance;
import com.epam.spring.homework.mvc.repairAgency.repository.InvoiceBalanceRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.InvoiceBalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class InvoiceBalanceServiceTest {

    @InjectMocks
    private InvoiceBalanceServiceImpl invoiceBalanceService;

    @Mock
    private InvoiceBalanceRepository invoiceBalanceRepository;

    @Spy
    private InvoiceBalanceMapper invoiceBalanceMapper = Mappers.getMapper(InvoiceBalanceMapper.class);

    private static final BigDecimal TEST_VALUE = BigDecimal.valueOf(200);

    @Test
    void getInvoiceBalanceByIdTest(){

        //given
        InvoiceBalance expectedInvoiceBalance = new InvoiceBalance();
        expectedInvoiceBalance.setAmmount(TEST_VALUE);
        when(invoiceBalanceRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(expectedInvoiceBalance));

        //when
        InvoiceBalanceDto actualInvoiceBalance = invoiceBalanceService.get(1L);

        //then
        assertEquals(expectedInvoiceBalance.getAmmount(), actualInvoiceBalance.getAmmount());
    }

    @Test
    void getAllInvoiceBalancesTest() {

        when(invoiceBalanceRepository.findAll()).thenReturn(Arrays.asList(new InvoiceBalance(), new InvoiceBalance()));

        List<InvoiceBalanceDto> data = invoiceBalanceService.getAll();

        assertEquals(2, data.size());
    }

    @Test
    void createInvoiceBalanceTest() {

        //given
        InvoiceBalance expectedInvoiceBalance = new InvoiceBalance();
        expectedInvoiceBalance.setAmmount(TEST_VALUE);
        when(invoiceBalanceRepository.save((any(InvoiceBalance.class)))).thenReturn(expectedInvoiceBalance);

        //when
        InvoiceBalanceDto actualInvoiceBalance = invoiceBalanceService.add(invoiceBalanceMapper.mapInvoiceDto(expectedInvoiceBalance));

        //then
        assertEquals(expectedInvoiceBalance.getAmmount(), actualInvoiceBalance.getAmmount());
    }

     @Test
    void updateInvoiceBalanceTest() {

        //given
        InvoiceBalance expectedInvoiceBalance = new InvoiceBalance();

        InvoiceBalanceDto updatedInvoiceBalance = invoiceBalanceMapper.mapInvoiceDto(expectedInvoiceBalance);
        updatedInvoiceBalance.setAmmount(TEST_VALUE);

        when(invoiceBalanceRepository.findById(1L)).thenReturn(Optional.of(expectedInvoiceBalance));

        //when
        InvoiceBalanceDto actualInvoiceBalance = invoiceBalanceService.update(1L, updatedInvoiceBalance);

        //then
        assertEquals(TEST_VALUE, actualInvoiceBalance.getAmmount());
    }

    @Test
    void updateAmmountInvoiceBalanceTest() {

        //given
        InvoiceBalance expectedInvoiceBalance = new InvoiceBalance();

        InvoiceBalanceDto updatedInvoiceBalance = invoiceBalanceMapper.mapInvoiceDto(expectedInvoiceBalance);

        when(invoiceBalanceRepository.findById(1L)).thenReturn(Optional.of(expectedInvoiceBalance));

        //when
        InvoiceBalanceDto actualInvoiceBalance = invoiceBalanceService.updateAmmount(1L, TEST_VALUE);

        //then
        assertEquals(TEST_VALUE, actualInvoiceBalance.getAmmount());
    }
}
