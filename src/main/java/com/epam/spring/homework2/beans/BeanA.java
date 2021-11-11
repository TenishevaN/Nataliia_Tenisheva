package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements InitializingBean, DisposableBean, Validator {
    private String name;
    private int value;

    public BeanA() {
        System.out.println(this);
    }

    public BeanA(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanA";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy method BeanA");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet method BeanA");
    }

    @Override
    public void validate() {
        String result = ValidateUtil.vaidateResult(name, value);
        if (!"".equals(result)){
            System.out.println(this.getClass().getSimpleName() +" " + result);
        }
    }
}
