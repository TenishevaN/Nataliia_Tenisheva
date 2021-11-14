package com.epam.spring.homework.mvc.repairAgency.controller;


import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/invoice")
    public InvoiceDto addInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.add(invoiceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/invoice/{id}")
    public InvoiceDto updateInvoice(@PathVariable int id, @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.update(id, invoiceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice")
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice/{id}")
    public InvoiceDto getInvoice(@PathVariable int id) {
        return invoiceService.get(id);
    }

    @DeleteMapping(value = "/invoice/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
