package com.epam.spring.homework.mvc.repairAgency.controller;


import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceBalanceController {

    private final InvoiceBalanceService invoiceBalanceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/invoice-balance")
    public InvoiceBalanceDto addInvoiceBalance(@Valid @RequestBody InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalanceDto invoiceBalance = invoiceBalanceService.add(invoiceBalanceDto);
        if (invoiceBalance == null) {
            throw new NullPointerException();
        }
        return invoiceBalance;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/invoice-balance/{id}")
    public InvoiceBalanceDto updateInvoiceBalance(@PathVariable int id, @Valid @RequestBody InvoiceBalanceDto invoiceBalanceDto) {

        InvoiceBalanceDto invoiceBalance = invoiceBalanceService.update(id, invoiceBalanceDto);
        if (invoiceBalance == null) {
            throw new NullPointerException();
        }
        return invoiceBalance;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/invoice-balance/{id}/{ammount}")
    public InvoiceBalanceDto updateInvoiceBalanceAmmount(@PathVariable int id, @PathVariable BigDecimal ammount) {

        InvoiceBalanceDto invoiceBalance = invoiceBalanceService.updateAmmount(id, ammount);
        if (invoiceBalance == null) {
            throw new NullPointerException();
        }
        return invoiceBalance;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice-balance")
    public List<InvoiceBalanceDto> getAllInvoiceBalances() {
        return invoiceBalanceService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice-balance/{id}")
    public InvoiceBalanceDto getInvoiceBalance(@PathVariable int id) {

        InvoiceBalanceDto invoiceBalance = invoiceBalanceService.get(id);
        if (invoiceBalance == null) {
            throw new NullPointerException();
        }
        return invoiceBalance;
    }
}
