package com.springboot.fullstack.mapper;

import com.springboot.fullstack.dto.InvoiceBalanceDto;
import com.springboot.fullstack.model.InvoiceBalance;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceBalanceMapper {

    InvoiceBalanceDto mapInvoiceDto(InvoiceBalance invoiceBalance);
    InvoiceBalance mapInvoice(InvoiceBalanceDto dto);
}