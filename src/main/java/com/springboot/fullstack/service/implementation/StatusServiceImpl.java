package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.StatusDto;
import com.springboot.fullstack.model.Status;
import com.springboot.fullstack.repository.StatusRepository;
import com.springboot.fullstack.service.StatusService;
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
    public StatusDto get(final Long idStatus, final String locale) {
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
