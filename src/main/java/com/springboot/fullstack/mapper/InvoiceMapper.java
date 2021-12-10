package com.springboot.fullstack.mapper;


import com.springboot.fullstack.dto.InvoiceDto;
import com.springboot.fullstack.model.Invoice;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {

    InvoiceDto mapInvoiceDto(Invoice invoices);
    Invoice mapInvoice(InvoiceDto dto);
}