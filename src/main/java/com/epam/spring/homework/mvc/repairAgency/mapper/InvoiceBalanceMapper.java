package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.model.InvoiceBalance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface InvoiceBalanceMapper {
    InvoiceBalanceMapper INSTANCE = Mappers.getMapper(InvoiceBalanceMapper.class);
    Set<InvoiceBalanceDto> mapInvoiceDtos(Set<InvoiceBalance> invoiceBalances);
    InvoiceBalanceDto mapInvoiceDto(InvoiceBalance invoiceBalance);
    InvoiceBalance mapInvoice(InvoiceBalanceDto dto);
}