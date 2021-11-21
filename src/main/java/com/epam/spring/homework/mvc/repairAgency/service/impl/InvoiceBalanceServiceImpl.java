package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.InvoiceBalanceMapper;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import com.epam.spring.homework.mvc.repairAgency.model.InvoiceBalance;
import com.epam.spring.homework.mvc.repairAgency.repository.InvoiceBalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceBalanceServiceImpl implements InvoiceBalanceService {

    private final InvoiceBalanceRepository invoiceBalanceRepository;
    private final InvoiceBalanceMapper invoiceBalanceMapper;

    @Override
    public InvoiceBalanceDto get(final int id) {

        InvoiceBalance invoiceBalance = invoiceBalanceRepository.get(id);
        return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
    }


    @Override
    public List<InvoiceBalanceDto> getAll() {

        return invoiceBalanceRepository.getAll()
                .stream()
                .map(invoiceBalanceMapper::mapInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceBalanceDto add(final InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalance invoiceBalance = invoiceBalanceMapper.mapInvoice(invoiceBalanceDto);
        invoiceBalance = invoiceBalanceRepository.add(invoiceBalance);
        return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
    }


    @Override
    public InvoiceBalanceDto update(final int id, final InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalance invoiceBalance =  invoiceBalanceMapper.mapInvoice(invoiceBalanceDto);
        invoiceBalance = invoiceBalanceRepository.update(id, invoiceBalance);
        return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
    }

    @Override
    public InvoiceBalanceDto updateAmmount(int id, BigDecimal ammount) {

        InvoiceBalance invoiceBalance = invoiceBalanceRepository.updateAmmount(id, ammount);
        return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
    }
}
