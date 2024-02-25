package com.shreesh.fakestore.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationConfigurations {

    @Bean
    RestTemplate getRestTempLate()
    {
        return new RestTemplateBuilder().build();
    }

}
