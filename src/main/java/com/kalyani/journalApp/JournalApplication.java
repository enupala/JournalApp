package com.kalyani.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(JournalApplication.class, args);
        System.out.println(applicationContext.getEnvironment().getActiveProfiles()[0]);
    }
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory  mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}