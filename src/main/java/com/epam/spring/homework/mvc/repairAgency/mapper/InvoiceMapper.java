package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);
    Set<InvoiceDto> mapInvoiceDtos(Set<Invoice> invoices);
    InvoiceDto mapInvoiceDto(Invoice invoices);
    Invoice mapInvoice(InvoiceDto dto);
}