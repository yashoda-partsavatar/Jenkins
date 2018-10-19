package com.yashoda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HooksApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HooksApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HooksApp.class, args);
    }

    @Configuration
    @Profile("local")
    @ComponentScan(basePackages = "com.yashoda", lazyInit = true)
    static class LocalConfig {
    }

    @Configuration
    @Profile("default")
    @ComponentScan(basePackages = "com.yashoda")
    static class DefaultConfig {
    }

}
