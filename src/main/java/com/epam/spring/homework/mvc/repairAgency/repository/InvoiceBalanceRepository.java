package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.InvoiceBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceBalanceRepository extends JpaRepository<InvoiceBalance, Long> {

    List<InvoiceBalance> findAll();

    InvoiceBalance save(InvoiceBalance invoiceBalance);


}
