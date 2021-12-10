package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.InvoiceDto;
import com.springboot.fullstack.mapper.InvoiceMapper;
import com.springboot.fullstack.model.Invoice;
import com.springboot.fullstack.repository.InvoiceRepository;
import com.springboot.fullstack.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDto get(final Long id) {

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            return invoiceMapper.mapInvoiceDto(invoiceOptional.get());
        }
        return null;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::mapInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto add(final InvoiceDto invoiceDto) {

        Invoice invoice = invoiceMapper.mapInvoice(invoiceDto);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.mapInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto update(final Long id, final InvoiceDto invoiceDto) {

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setAmmount(invoiceDto.getAmmount());

            invoiceRepository.save(invoice);
            log.info("invoice Updated {}", invoice);
            return invoiceMapper.mapInvoiceDto(invoice);
        }
        return null;
    }

    @Override
    public boolean delete(final Long id) {
        log.info("delete Invoice by id {}", id);
        invoiceRepository.deleteById(Long.valueOf(id));
        return true;
    }

}
