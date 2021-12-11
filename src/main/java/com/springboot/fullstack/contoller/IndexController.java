package com.springboot.fullstack.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public String getFirstPage() {
        return "index";
    }
}
