package com.springboot.fullstack.repository;

import com.springboot.fullstack.model.RepairRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRequestRepository extends PagingAndSortingRepository<RepairRequest, Long> {

    List<RepairRequest> findAll();

    RepairRequest save(RepairRequest repairRequest);
}
