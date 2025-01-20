package org.example.gymcrmsystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan(basePackages = "org.example.gymcrmsystem")
public class TestAppConfig {
}
