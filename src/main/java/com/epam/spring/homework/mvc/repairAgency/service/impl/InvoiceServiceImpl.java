package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.InvoiceMapper;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceService;
import com.epam.spring.homework.mvc.repairAgency.model.Invoice;
import com.epam.spring.homework.mvc.repairAgency.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDto get(final int id) {
        Invoice invoice = invoiceRepository.get(id);
        return invoiceMapper.mapInvoiceDto(invoice);
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.getAll()
                .stream()
                .map(invoiceMapper::mapInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto add(final InvoiceDto invoiceDto) {

        Invoice invoice = invoiceMapper.mapInvoice(invoiceDto);
        invoice = invoiceRepository.add(invoice);
        return invoiceMapper.mapInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto update(final int id, final InvoiceDto invoiceDto) {

        Invoice invoice = invoiceMapper.mapInvoice(invoiceDto);
        invoice = invoiceRepository.update(id, invoice);
        return invoiceMapper.mapInvoiceDto(invoice);
    }

    @Override
    public boolean delete(final int id) {
        return invoiceRepository.delete(id);
    }

}
