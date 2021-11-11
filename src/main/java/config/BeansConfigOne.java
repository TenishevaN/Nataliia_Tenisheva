package config;

import com.epam.spring.homework2.beans.BeanFactoryPostProcessorCustom;
import com.epam.spring.homework2.beans.BeanPostProcessorCustom;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@Import(BeansConfigTwo.class)
public class BeansConfigOne {

    @Bean
    public BeanFactoryPostProcessorCustom beanFactoryPostProcessorCustom() {
        return new BeanFactoryPostProcessorCustom();
    }

    @Bean
    public BeanPostProcessorCustom beanPostProcessorCustom() {
        return new BeanPostProcessorCustom();
    }
}
