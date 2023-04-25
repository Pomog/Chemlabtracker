package lv.javaguru.java2.servify.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.servify")
@PropertySource(value = "classpath:application.properties")
public class DetailListConfiguration {

}
