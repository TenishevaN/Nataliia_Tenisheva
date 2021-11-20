package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
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

    @Override
    public RepairRequestDto get(final int id) {
        RepairRequest repairRequest = repairRequestRepository.get(id);
        return mapRepairRequestToRepairRequestDto(repairRequest);
    }

    @Override
    public List<RepairRequestDto> getAll() {
        return repairRequestRepository.getAll()
                .stream()
                .map(this::mapRepairRequestToRepairRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RepairRequestDto add(final RepairRequestDto repairRequestDto) {
        RepairRequest repairRequest = mapRepairRequestDtoToRepairRequest(repairRequestDto);
        repairRequest = repairRequestRepository.add(repairRequest);
        return mapRepairRequestToRepairRequestDto(repairRequest);
    }

    @Override
    public RepairRequestDto update(final int id, final RepairRequestDto repairRequestDto) {

        RepairRequest repairRequest = mapRepairRequestDtoToRepairRequest(repairRequestDto);
        repairRequest = repairRequestRepository.update(id, repairRequest);
        return mapRepairRequestToRepairRequestDto(repairRequest);
    }

    @Override
    public boolean delete(int id) {
        repairRequestRepository.delete(id);
        return false;
    }

    private RepairRequestDto mapRepairRequestToRepairRequestDto(final RepairRequest repairRequest) {
        return RepairRequestDto.builder()
                .userId(repairRequest.getUserId())
                .userName(repairRequest.getUserName())
                .statusId(repairRequest.getStatusId())
                .statusName(repairRequest.getStatusName())
                .masterId(repairRequest.getMasterId())
                .masterName(repairRequest.getMasterName())
                .cost(repairRequest.getCost())
                .date(repairRequest.getDate())
                .description(repairRequest.getDescription())
                .build();
    }

    private RepairRequest mapRepairRequestDtoToRepairRequest(final RepairRequestDto repairRequestDto) {
        return RepairRequest.builder()
                .userId(repairRequestDto.getUserId())
                .userName(repairRequestDto.getUserName())
                .statusId(repairRequestDto.getStatusId())
                .statusName(repairRequestDto.getStatusName())
                .masterId(repairRequestDto.getMasterId())
                .masterName(repairRequestDto.getMasterName())
                .cost(repairRequestDto.getCost())
                .date(repairRequestDto.getDate())
                .description(repairRequestDto.getDescription())
                .build();
    }
}
