package com.springboot.fullstack.contoller;


import com.springboot.fullstack.dto.StatusDto;
import com.springboot.fullstack.service.StatusService;
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
    public StatusDto getStatus(@PathVariable Long id, @PathVariable String locale) {

        StatusDto status = statusService.get(id, locale);
        if (status == null) {
            throw new NullPointerException();
        }
        return status;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/status/{locale}")
    public List<StatusDto> getAllStatuses(@PathVariable String locale) {
        return statusService.getAll(locale);
    }

}
