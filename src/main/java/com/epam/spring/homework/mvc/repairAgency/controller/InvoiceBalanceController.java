package com.epam.spring.homework.mvc.repairAgency.controller;


import com.epam.spring.homework.mvc.repairAgency.controller.dto.InvoiceBalanceDto;
import com.epam.spring.homework.mvc.repairAgency.controller.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.service.InvoiceBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceBalanceController {

    private final InvoiceBalanceService invoiceBalanceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/invoice-balance")
    public InvoiceBalanceDto addInvoiceBalance(@RequestBody InvoiceBalanceDto invoiceBalanceDto) {
        return invoiceBalanceService.add(invoiceBalanceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/invoice-balance/{id}")
    public InvoiceBalanceDto updateInvoiceBalance(@PathVariable int id, @RequestBody InvoiceBalanceDto invoiceBalanceDto) {
        return invoiceBalanceService.update(id, invoiceBalanceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/invoice-balance/{id}/{ammount}")
    public InvoiceBalanceDto updateInvoiceBalanceAmmount(@PathVariable int id, @PathVariable BigDecimal ammount) {
        return invoiceBalanceService.updateAmmount(id, ammount);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice-balance")
    public List<InvoiceBalanceDto> getAllInvoiceBalances() {
        return invoiceBalanceService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/invoice-balance/{id}")
    public InvoiceBalanceDto getInvoiceBalance(@PathVariable int id) {
        return invoiceBalanceService.get(id);
    }

}
