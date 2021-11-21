package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.RepairRequestDto;
import com.epam.spring.homework.mvc.repairAgency.model.RepairRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface RepairRequestMapper {
    RepairRequestMapper INSTANCE = Mappers.getMapper(RepairRequestMapper.class);
    Set<RepairRequestDto> mapRepairRequestDtos(Set<RepairRequest> repairRequests);
    RepairRequestDto mapRepairRequestDto(RepairRequest repairRequests);
    RepairRequest mapRepairRequest(RepairRequestDto dto);
}