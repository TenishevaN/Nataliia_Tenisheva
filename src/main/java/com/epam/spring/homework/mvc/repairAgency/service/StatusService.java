package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto get(Long idStatus, String locale);

    List<StatusDto> getAll(String locale);
}
