package com.epam.spring.homework1.config;

import com.epam.spring.homework1.pet.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.homework1.pet"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Spider.class)})
public class PetConfig {

    @Qualifier("cheetahBean")
    @Bean
    public Cheetah cheetah() {
        return new Cheetah();
    }

    @Bean
    public Cheetah getCheetah(@Qualifier("cheetahBean") Cheetah cheetah) {
        return cheetah;
    }

    @Bean
    @Primary
    public Cheetah primaryCheetah() {
        return new Cheetah();
    }
}


