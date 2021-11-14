package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.RepairRequestDto;

import java.util.List;

public interface RepairRequestService {

    RepairRequestDto get(int id);

    List<RepairRequestDto> getAll();

    RepairRequestDto add(RepairRequestDto repairRequestDto);

    RepairRequestDto update(int id, RepairRequestDto repairRequestDto);

    void delete(int id);
}
