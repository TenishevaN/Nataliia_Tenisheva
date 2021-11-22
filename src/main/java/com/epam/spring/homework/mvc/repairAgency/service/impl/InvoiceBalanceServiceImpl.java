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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceBalanceServiceImpl implements InvoiceBalanceService {

    private final InvoiceBalanceRepository invoiceBalanceRepository;
    private final InvoiceBalanceMapper invoiceBalanceMapper;

    @Override
    public InvoiceBalanceDto get(final Long id) {

        Optional<InvoiceBalance> invoiceBalanceOptional = invoiceBalanceRepository.findById(id);
        if (invoiceBalanceOptional.isPresent()) {
            return invoiceBalanceMapper.mapInvoiceDto(invoiceBalanceOptional.get());
        }
        return null;
    }


    @Override
    public List<InvoiceBalanceDto> getAll() {

        return invoiceBalanceRepository.findAll()
                .stream()
                .map(invoiceBalanceMapper::mapInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceBalanceDto add(final InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalance invoiceBalance = invoiceBalanceMapper.mapInvoice(invoiceBalanceDto);
        invoiceBalance = invoiceBalanceRepository.save(invoiceBalance);
        return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
    }


    @Override
    public InvoiceBalanceDto update(final Long id, final InvoiceBalanceDto invoiceBalanceDto) {

        Optional<InvoiceBalance> invoiceBalanceOptional = invoiceBalanceRepository.findById(id);
        if (invoiceBalanceOptional.isPresent()) {
            InvoiceBalance invoiceBalance = invoiceBalanceOptional.get();
            invoiceBalance.setAmmount(invoiceBalanceDto.getAmmount());

            invoiceBalanceRepository.save(invoiceBalance);
            log.info("invoiceBalance Updated {}", invoiceBalance);
            return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
        }
        return null;
    }

    @Override
    public InvoiceBalanceDto updateAmmount(final Long id, final BigDecimal ammount) {

        Optional<InvoiceBalance> optionalInvoiceBalance = invoiceBalanceRepository.findById(id);
        log.info("invoiceBalance {}", optionalInvoiceBalance);
        if (optionalInvoiceBalance.isPresent()) {
            InvoiceBalance invoiceBalance = optionalInvoiceBalance.get();
            invoiceBalance.setAmmount(ammount);
            invoiceBalanceRepository.save(invoiceBalance);
            log.info("invoiceBalanceUpdated {}", invoiceBalance);
            return invoiceBalanceMapper.mapInvoiceDto(invoiceBalance);
        }
        return null;
    }
}
