package com.kalyani.journalApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppConfig {

    @Value("${spring.application.name}")
    public String appName;

    @PostConstruct
    public void init() {
        System.out.println("App Name from YAML: " + appName);
    }
}
