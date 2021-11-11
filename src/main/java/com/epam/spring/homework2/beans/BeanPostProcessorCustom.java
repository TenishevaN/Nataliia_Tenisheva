package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorCustom implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof Validator) {
            ((Validator) bean).validate();
        }
        return bean;
    }
}
