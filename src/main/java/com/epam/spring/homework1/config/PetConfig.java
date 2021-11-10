package com.epam.spring.homework1.config;

import com.epam.spring.homework1.Pet;
import com.epam.spring.homework1.pet.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type =  FilterType.ASSIGNABLE_TYPE, value = Spider.class)})
public class PetConfig {

    @Bean
    public Dog dog(){
        return new Dog();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }

    @Qualifier("cheetahBean")
    @Bean
    public Cheetah cheetah(){
        return new Cheetah();
    }

    @Bean
    public Spider spider(){
        return new Spider();
    }

    @Bean
    public Pet pet(){
        return new Pet();
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


