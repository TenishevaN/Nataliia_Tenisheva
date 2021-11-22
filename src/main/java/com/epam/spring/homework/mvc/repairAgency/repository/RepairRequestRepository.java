package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRequestRepository extends PagingAndSortingRepository<RepairRequest, Long> {

    List<RepairRequest> findAll();

    RepairRequest save(RepairRequest repairRequest);
}
