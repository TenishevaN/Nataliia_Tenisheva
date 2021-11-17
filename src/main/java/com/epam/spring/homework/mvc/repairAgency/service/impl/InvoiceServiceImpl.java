package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceService;
import com.epam.spring.homework.mvc.repairAgency.service.model.Invoice;
import com.epam.spring.homework.mvc.repairAgency.service.repository.InvoiceRepository;
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

    @Override
    public InvoiceDto get(final int id) {
        Invoice invoice = invoiceRepository.get(id);
        return mapInvoiceToInvoiceDto(invoice);
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.getAll()
                .stream()
                .map(this::mapInvoiceToInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto add(final InvoiceDto invoiceDto) {

        Invoice invoice = mapInvoiceDtoToInvoice(invoiceDto);
        invoice = invoiceRepository.add(invoice);
        return mapInvoiceToInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto update(final int id, final InvoiceDto invoiceDto) {

        Invoice invoice = mapInvoiceDtoToInvoice(invoiceDto);
        invoice = invoiceRepository.update(id, invoice);
        return mapInvoiceToInvoiceDto(invoice);
    }

    @Override
    public boolean delete(final int id) {
        return invoiceRepository.delete(id);
    }

    private InvoiceDto mapInvoiceToInvoiceDto(final Invoice invoice) {
        return InvoiceDto.builder()
                .account_id(invoice.getAccount_id())
                .ammount(invoice.getAmmount())
                .build();
    }

    private Invoice mapInvoiceDtoToInvoice(final InvoiceDto invoiceDto) {
        return Invoice.builder()
                .account_id(invoiceDto.getAccount_id())
                .ammount(invoiceDto.getAmmount())
                .build();
    }

}
