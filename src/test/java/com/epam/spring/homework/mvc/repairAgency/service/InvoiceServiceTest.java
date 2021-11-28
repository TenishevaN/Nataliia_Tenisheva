package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.InvoiceMapper;
import com.epam.spring.homework.mvc.repairAgency.model.Invoice;
import com.epam.spring.homework.mvc.repairAgency.repository.InvoiceRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.InvoiceServiceImpl;
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
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Spy
    private InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);

    private static final BigDecimal TEST_VALUE = BigDecimal.valueOf(200);

    @Test
    void getInvoiceIdTest() {

        //given
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setAmmount(TEST_VALUE);
        when(invoiceRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(expectedInvoice));

        //when
        InvoiceDto actualInvoice = invoiceService.get(1L);

        //then
        assertEquals(expectedInvoice.getAmmount(), actualInvoice.getAmmount());
    }

    @Test
    void getAllInvoicesTest() {

        when(invoiceRepository.findAll()).thenReturn(Arrays.asList(new Invoice(), new Invoice()));

        List<InvoiceDto> data = invoiceService.getAll();

        assertEquals(2, data.size());
    }

    @Test
    void createInvoiceTest() {

        //given
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setAmmount(TEST_VALUE);
        when(invoiceRepository.save((any(Invoice.class)))).thenReturn(expectedInvoice);

        //when
        InvoiceDto actualInvoice = invoiceService.add(invoiceMapper.mapInvoiceDto(expectedInvoice));

        //then
        assertEquals(expectedInvoice.getAmmount(), actualInvoice.getAmmount());
    }

    @Test
    void updateInvoiceTest() {

        //given
        Invoice expectedInvoice = new Invoice();

        InvoiceDto updatedInvoice = invoiceMapper.mapInvoiceDto(expectedInvoice);
        updatedInvoice.setAmmount(TEST_VALUE);

        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(expectedInvoice));

        //when
        InvoiceDto actualInvoice = invoiceService.update(1L, updatedInvoice);

        //then
        assertEquals(TEST_VALUE, actualInvoice.getAmmount());
    }

    @Test
    void deleteInvoiceTest() {

        //given
        doNothing().when(invoiceRepository).deleteById(1L);

        //when
        invoiceService.delete(1L);
        invoiceService.delete(1L);

        //then
        verify(invoiceRepository, times(2)).deleteById(1L);
    }
}
