package com.epam.spring.homework.mvc.repairAgency.service.impl;


import com.epam.spring.homework.mvc.repairAgency.controller.dto.StatusDto;
import com.epam.spring.homework.mvc.repairAgency.service.StatusService;
import com.epam.spring.homework.mvc.repairAgency.service.model.Status;
import com.epam.spring.homework.mvc.repairAgency.service.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public StatusDto get(final int idStatus, final String locale) {
        log.info("getStatus by id {}", idStatus);
        Status status = statusRepository.get(idStatus, locale);
        return mapStatusToStatusDto(status);
    }

    @Override
    public List<StatusDto> getAll(final String locale) {
        log.info("get all statuses");
        return statusRepository.getAll(locale)
                .stream()
                .map(this::mapStatusToStatusDto)
                .collect(Collectors.toList());
    }


    private StatusDto mapStatusToStatusDto(final Status status) {
        return StatusDto.builder()
                .name(status.getName())
                .build();
    }
}
