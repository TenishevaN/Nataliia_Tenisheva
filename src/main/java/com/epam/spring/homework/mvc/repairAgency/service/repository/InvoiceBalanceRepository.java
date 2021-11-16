package com.epam.spring.homework.mvc.repairAgency.service.repository;

import com.epam.spring.homework.mvc.repairAgency.service.model.InvoiceBalance;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceBalanceRepository {

    InvoiceBalance get(int id);

    List<InvoiceBalance> getAll();

    InvoiceBalance add(InvoiceBalance invoiceBalance);

    InvoiceBalance update(int id, InvoiceBalance invoiceBalance);

    BigDecimal getTotal(int id);

    BigDecimal getBalanceOwed(int id);

    InvoiceBalance updateAmmount(int id, BigDecimal ammount);
}
