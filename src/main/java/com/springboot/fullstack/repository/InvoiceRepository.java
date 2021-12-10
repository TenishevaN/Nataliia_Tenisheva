package com.springboot.fullstack.repository;

import com.springboot.fullstack.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAll();

    Invoice save(Invoice invoice);

}
