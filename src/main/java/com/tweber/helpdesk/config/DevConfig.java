package com.tweber.helpdesk.config;

import com.tweber.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.dll-auto}")
    private String value;

    @Bean
    public void instanciarDB() {
        if (value.equals("update")) {
            this.dbService.instanciarDB();
        }
    }
}
