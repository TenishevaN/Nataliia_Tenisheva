package com.epam.spring.homework.mvc.repairAgency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ErrorController {

    @GetMapping(value = "/error-page")
    public void error(){
        throw new NullPointerException();
    }

}
