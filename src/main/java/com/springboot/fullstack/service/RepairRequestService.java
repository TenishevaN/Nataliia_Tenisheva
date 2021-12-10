package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.RepairRequestDto;

import java.util.List;

public interface RepairRequestService {

    RepairRequestDto get(Long id);

    List<RepairRequestDto> getAll();

    List<RepairRequestDto> getAllSortedAndPaginated(int page);

    RepairRequestDto add(RepairRequestDto repairRequestDto);

    RepairRequestDto update(Long id, RepairRequestDto repairRequestDto);

    boolean delete(Long id);
}
