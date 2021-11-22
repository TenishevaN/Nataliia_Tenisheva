package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceBalanceService {

    InvoiceBalanceDto get(Long id);

    List<InvoiceBalanceDto> getAll();

    InvoiceBalanceDto add(InvoiceBalanceDto invoiceBalanceDto);

    InvoiceBalanceDto update(Long id, InvoiceBalanceDto invoiceBalanceDto);

    InvoiceBalanceDto updateAmmount(Long id, BigDecimal ammount);
}
