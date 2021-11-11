package config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("application.properties")
public class BeansConfigTwo {

        @Bean(
                initMethod = "customInitMethodB",
                destroyMethod = "customDestroyMethodB"
        )
        public BeanB getMyBeanB() {
                return new BeanB();
        }

        @Bean(
                initMethod = "customInitMethodC",
                destroyMethod = "customDestroyMethodC"
        )
        public BeanC getMyBeanC() {
                return new BeanC();
        }

        @Bean(
                initMethod = "customInitMethodD",
                destroyMethod = "customDestroyMethodD"
        )
        public BeanD getMyBeanD() {
            return new BeanD();
        }
}

