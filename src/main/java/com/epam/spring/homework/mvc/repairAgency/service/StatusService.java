package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto get(int idStatus, String locale);

    List<StatusDto> getAll(String locale);
}
