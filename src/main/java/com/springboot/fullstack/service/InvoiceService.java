package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    InvoiceDto get(Long id);

    List<InvoiceDto> getAll();

    InvoiceDto add(InvoiceDto invoiceDto);

    InvoiceDto update(Long id, InvoiceDto invoiceDto);

    boolean delete(Long id);

}
