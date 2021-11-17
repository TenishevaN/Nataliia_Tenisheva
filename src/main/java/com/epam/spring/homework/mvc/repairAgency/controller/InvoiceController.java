package com.epam.spring.homework.mvc.repairAgency.controller;


import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/invoice")
    public InvoiceDto addInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {

        InvoiceDto invoice = invoiceService.add(invoiceDto);
        if (invoice == null) {
            throw new NullPointerException();
        }
        return invoice;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/invoice/{id}")
    public InvoiceDto updateInvoice(@PathVariable int id, @Valid @RequestBody InvoiceDto invoiceDto) {

        InvoiceDto findedInvoiceDto = invoiceService.update(id, invoiceDto);
        if (findedInvoiceDto == null) {
            throw new NullPointerException();
        }
        return findedInvoiceDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice")
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice/{id}")
    public InvoiceDto getInvoice(@PathVariable int id) {

        InvoiceDto invoice = invoiceService.get(id);
        if (invoice == null) {
            throw new NullPointerException();
        }
        return invoice;
    }

    @DeleteMapping(value = "/invoice/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
        boolean invoiceDeleted = invoiceService.delete(id);
        if (!invoiceDeleted) {
            throw new EntityNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }
}
