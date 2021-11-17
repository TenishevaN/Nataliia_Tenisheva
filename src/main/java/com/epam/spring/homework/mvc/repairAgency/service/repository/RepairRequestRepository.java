package com.epam.spring.homework.mvc.repairAgency.service.repository;

import com.epam.spring.homework.mvc.repairAgency.service.model.RepairRequest;

import java.util.List;

public interface RepairRequestRepository {

    RepairRequest get(int id);

    List<RepairRequest> getAll();

    RepairRequest add(RepairRequest repairRequest);

    RepairRequest update(int id, RepairRequest repairRequest);

    boolean delete(int id);
}
