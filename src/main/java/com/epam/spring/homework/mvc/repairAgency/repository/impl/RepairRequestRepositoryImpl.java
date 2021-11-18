package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.service.model.RepairRequest;
import com.epam.spring.homework.mvc.repairAgency.service.repository.RepairRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RepairRequestRepositoryImpl implements RepairRequestRepository, GenerationId {

    private final List<RepairRequest> list = new ArrayList<>();

    @Override
    public RepairRequest get(final int id) {
        return list.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("RepairRequest is not found!"));
    }

    @Override
    public List<RepairRequest> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public RepairRequest add(final RepairRequest repairRequest) {

        if(repairRequest.getId() == 0){
            List<Integer> idList = list
                    .stream()
                    .map(x -> x.getId()).collect(Collectors.toList());
            int id = generateId(idList);
            log.info("id for new  RepairRequest {}",id);
            repairRequest.setId(id);
        }
        repairRequest.setDate(Instant.now());
        list.add(repairRequest);
        return repairRequest;
    }

    @Override
    public RepairRequest update(final int id, final RepairRequest repairRequest) {
        boolean isDeleted = list.removeIf(item -> item.getId() == id);
        if (isDeleted) {
            list.add(repairRequest);
        } else {
            throw new RuntimeException("RepairRequest is not found!");
        }
        return repairRequest;
    }

    @Override
    public void delete(int id) {
        list.removeIf(item -> item.getId() == id);
    }
}
