package com.kalyani.journalApp.config;


import com.sun.tools.javac.util.List;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig(){
        return  new OpenAPI().info(
                new Info().title("Journal App APIs")
                        .description("By Kalyani")
        ).servers(Arrays.asList(new Server().url("http://localhost:8070").description("local"),
                new Server().url("http://localhost:8071").description("prod"))
                ).tags(Arrays.asList(new Tag().name("Public APIs"),
                                     new Tag().name("User APIs"),
                                     new Tag().name("Journal APIs"),
                                     new Tag().name("Admin APIs")));
    }
}
