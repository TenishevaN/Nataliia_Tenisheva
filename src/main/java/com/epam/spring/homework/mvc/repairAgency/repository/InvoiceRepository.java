package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.Invoice;

import java.util.List;

public interface InvoiceRepository {


    Invoice get(int id);

    List<Invoice> getAll();

    Invoice add(Invoice invoice);

    Invoice update(int id, Invoice invoice);

    boolean delete(int id);

}
