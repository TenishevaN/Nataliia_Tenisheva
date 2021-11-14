package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.StatusDto;
import com.epam.spring.homework.mvc.repairAgency.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/status/{locale}/{id}")
    public StatusDto getStatus(@PathVariable int id, @PathVariable String locale) {
        return statusService.get(id, locale);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/status/{locale}")
    public List<StatusDto> getAllStatuses(@PathVariable String locale) {
        return statusService.getAll(locale);
    }

}
