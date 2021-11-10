package com.epam.spring.homework1.config;

import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("com.epam.spring.homework1.other")
@Import({ PetConfig.class })
public class OtherConfig {
}
