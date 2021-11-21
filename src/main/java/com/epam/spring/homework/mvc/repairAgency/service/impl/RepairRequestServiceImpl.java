package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.RepairRequestMapper;
import com.epam.spring.homework.mvc.repairAgency.mapper.ReviewMapper;
import com.epam.spring.homework.mvc.repairAgency.service.RepairRequestService;
import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;
import com.epam.spring.homework.mvc.repairAgency.repository.RepairRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final RepairRequestMapper repairRequestMapper;

    @Override
    public RepairRequestDto get(final int id) {
        RepairRequest repairRequest = repairRequestRepository.get(id);
        return repairRequestMapper.mapRepairRequestDto(repairRequest);
    }

    @Override
    public List<RepairRequestDto> getAll() {
        return repairRequestRepository.getAll()
                .stream()
                .map(repairRequestMapper::mapRepairRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RepairRequestDto add(final RepairRequestDto repairRequestDto) {
        RepairRequest repairRequest = repairRequestMapper.mapRepairRequest(repairRequestDto);
        repairRequest = repairRequestRepository.add(repairRequest);
        return repairRequestMapper.mapRepairRequestDto(repairRequest);
    }

    @Override
    public RepairRequestDto update(final int id, final RepairRequestDto repairRequestDto) {

        RepairRequest repairRequest = repairRequestMapper.mapRepairRequest(repairRequestDto);
        repairRequest = repairRequestRepository.update(id, repairRequest);
        return repairRequestMapper.mapRepairRequestDto(repairRequest);
    }

    @Override
    public boolean delete(int id) {
        repairRequestRepository.delete(id);
        return false;
    }
}
