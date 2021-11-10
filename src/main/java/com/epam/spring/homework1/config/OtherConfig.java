package com.epam.spring.homework1.config;

import com.epam.spring.homework1.beans.BeanA;
import com.epam.spring.homework1.other.OtherBeanA;
import com.epam.spring.homework1.other.OtherBeanB;
import com.epam.spring.homework1.other.OtherBeanC;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("com.epam.spring.homework1.other")
@Import({ PetConfig.class })
public class OtherConfig {

    @Bean
    public OtherBeanA otherBeanA(BeanA beanA) {
        return new OtherBeanA(beanA);
    }

    @Bean
    public OtherBeanB otherBeanB() {
        return new OtherBeanB();
    }

    @Bean
    public OtherBeanC otherBeanC() {
        return new OtherBeanC();
    }

}
