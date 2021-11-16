package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import com.epam.spring.homework.mvc.repairAgency.service.model.InvoiceBalance;
import com.epam.spring.homework.mvc.repairAgency.service.repository.InvoiceBalanceRepository;
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

    @Override
    public InvoiceBalanceDto get(final int id) {

        InvoiceBalance invoiceBalance = invoiceBalanceRepository.get(id);
        return mapInvoiceBalanceToInvoiceBalanceDto(invoiceBalance);
    }


    @Override
    public List<InvoiceBalanceDto> getAll() {

        return invoiceBalanceRepository.getAll()
                .stream()
                .map(this::mapInvoiceBalanceToInvoiceBalanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceBalanceDto add(final InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalance invoiceBalance = mapInvoiceBalanceDtoToInvoiceBalance(invoiceBalanceDto);
        invoiceBalance = invoiceBalanceRepository.add(invoiceBalance);
        return mapInvoiceBalanceToInvoiceBalanceDto(invoiceBalance);
    }


    @Override
    public InvoiceBalanceDto update(final int id, final InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalance invoiceBalance = mapInvoiceBalanceDtoToInvoiceBalance(invoiceBalanceDto);
        invoiceBalance = invoiceBalanceRepository.update(id, invoiceBalance);
        return mapInvoiceBalanceToInvoiceBalanceDto(invoiceBalance);
    }

    @Override
    public InvoiceBalanceDto updateAmmount(int id, BigDecimal ammount) {

        InvoiceBalance invoiceBalance = invoiceBalanceRepository.updateAmmount(id, ammount);
        return mapInvoiceBalanceToInvoiceBalanceDto(invoiceBalance);
    }

    private InvoiceBalanceDto mapInvoiceBalanceToInvoiceBalanceDto(final InvoiceBalance invoiceBalance) {

        return InvoiceBalanceDto.builder()
                .invoiceId(invoiceBalance.getInvoiceId())
                .repairRequestId(invoiceBalance.getRepairRequestId())
                .ammount(invoiceBalance.getAmmount())
                .build();
    }

    private InvoiceBalance mapInvoiceBalanceDtoToInvoiceBalance(final InvoiceBalanceDto invoiceBalanceDto) {

        return InvoiceBalance.builder()
                .invoiceId(invoiceBalanceDto.getInvoiceId())
                .repairRequestId(invoiceBalanceDto.getRepairRequestId())
                .ammount(invoiceBalanceDto.getAmmount())
                .build();
    }
}
