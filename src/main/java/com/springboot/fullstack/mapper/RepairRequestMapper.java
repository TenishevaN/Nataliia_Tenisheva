package com.springboot.fullstack.mapper;

import com.springboot.fullstack.dto.RepairRequestDto;
import com.springboot.fullstack.model.RepairRequest;
import org.mapstruct.Mapper;

@Mapper
public interface RepairRequestMapper {

    RepairRequestDto mapRepairRequestDto(RepairRequest repairRequests);
    RepairRequest mapRepairRequest(RepairRequestDto dto);
}