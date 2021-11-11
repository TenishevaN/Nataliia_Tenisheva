package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorCustom implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        for (String beanName : configurableListableBeanFactory.getBeanDefinitionNames()) {
            if ("beanB".equals(beanName)) {
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("beanB");
                beanDefinition.setInitMethodName("customInitMethodC");
                System.out.println("init method for beanB has changed: " + beanDefinition.getInitMethodName());
            }
        }
    }
}
