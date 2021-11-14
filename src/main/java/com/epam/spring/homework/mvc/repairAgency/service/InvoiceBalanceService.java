package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceBalanceDto;

import java.util.List;

public interface InvoiceBalanceService {

    InvoiceBalanceDto get(int id);

    List<InvoiceBalanceDto> getAll();

    InvoiceBalanceDto add(InvoiceBalanceDto invoiceBalanceDto);

    InvoiceBalanceDto update(int id, InvoiceBalanceDto invoiceBalanceDto);

}
