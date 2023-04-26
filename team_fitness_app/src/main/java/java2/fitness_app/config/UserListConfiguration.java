package java2.fitness_app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "java2.fitness_app")
@PropertySource(value = "classpath:application.properties")
public class UserListConfiguration {

}
