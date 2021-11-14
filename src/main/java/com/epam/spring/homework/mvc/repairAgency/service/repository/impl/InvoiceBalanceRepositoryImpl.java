package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.service.model.InvoiceBalance;
import com.epam.spring.homework.mvc.repairAgency.service.repository.InvoiceBalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class InvoiceBalanceRepositoryImpl implements InvoiceBalanceRepository, GenerationId {

    private final List<InvoiceBalance> list = new ArrayList<>();

    @Override
    public InvoiceBalance get(final int id) {
        return list.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("InvoiceBalance is not found!"));
    }

    @Override
    public List<InvoiceBalance> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public InvoiceBalance add(final InvoiceBalance invoiceBalance) {

        List<Integer> idList = list
                .stream()
                .map(x -> x.getId()).collect(Collectors.toList());
        int id = generateId(idList);
        log.info("id for new  invoiceBalance {}", id);
        if (invoiceBalance.getId() == 0) {
            invoiceBalance.setId(id);
        }
        list.add(invoiceBalance);
        return invoiceBalance;
    }

    @Override
    public InvoiceBalance update(final int id, final InvoiceBalance invoiceBalance) {

        boolean isDeleted = list.removeIf(item -> item.getId() == id);
        if (isDeleted) {
            invoiceBalance.setId(id);
            list.add(invoiceBalance);
        } else {
            throw new RuntimeException("invoiceBalance is not found!");
        }
        return invoiceBalance;
    }

    @Override
    public BigDecimal getTotal(final int id) {

        //request to DB
        return null;
    }

    @Override
    public BigDecimal getBalanceOwed(final int id) {

        //request to DB
        return null;
    }


}
