package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    InvoiceDto get(int id);

    List<InvoiceDto> getAll();

    InvoiceDto add(InvoiceDto invoiceDto);

    InvoiceDto update(int id, InvoiceDto invoiceDto);

    boolean delete(int id);

}
