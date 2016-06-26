package fr.clunven.docu.service;

import org.ff4j.FF4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public FF4j getFF4j() {
        return new FF4j("ff4j.xml");
    }
}
