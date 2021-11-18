package com.epam.spring.homework.mvc.repairAgency.service.repository;

import com.epam.spring.homework.mvc.repairAgency.service.model.Invoice;

import java.util.List;

public interface InvoiceRepository {


    Invoice get(int id);

    List<Invoice> getAll();

    Invoice add(Invoice invoice);

    Invoice update(int id, Invoice invoice);

    void delete(int id);

}
