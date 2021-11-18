package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;

import java.util.List;

public interface RepairRequestRepository {

    RepairRequest get(int id);

    List<RepairRequest> getAll();

    RepairRequest add(RepairRequest repairRequest);

    RepairRequest update(int id, RepairRequest repairRequest);

    boolean delete(int id);
}
