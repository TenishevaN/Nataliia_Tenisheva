package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto get(Long idStatus, String locale);

    List<StatusDto> getAll(String locale);
}
