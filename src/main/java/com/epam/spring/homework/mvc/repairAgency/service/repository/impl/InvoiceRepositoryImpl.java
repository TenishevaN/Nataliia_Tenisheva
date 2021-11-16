package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.service.model.Invoice;
import com.epam.spring.homework.mvc.repairAgency.service.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class InvoiceRepositoryImpl implements InvoiceRepository, GenerationId {

    private final List<Invoice> list = new ArrayList<>();

    @Override
    public Invoice get(final int id) {
        return list.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invoice is not found!"));
    }

    @Override
    public List<Invoice> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public Invoice add(final Invoice invoice) {

         if(invoice.getId() == 0){
            List<Integer> idList = list
                    .stream()
                    .map(x -> x.getId()).collect(Collectors.toList());
            int id = generateId(idList);
            log.info("id for new  invoice {}",id);
            invoice.setId(id);
        }
         list.add(invoice);
        return invoice;
    }

    @Override
    public Invoice update(final int id, final Invoice invoice) {

        boolean isDeleted = list.removeIf(item -> item.getId() == id);
        if (isDeleted) {
            list.add(invoice);
        } else {
            throw new RuntimeException("Invoice is not found!");
        }
        return invoice;
    }

    @Override
    public void delete(final int id) {
        list.removeIf(item -> item.getId() == id);
    }
}
