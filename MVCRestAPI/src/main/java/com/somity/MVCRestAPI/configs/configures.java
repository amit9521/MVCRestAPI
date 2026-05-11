package com.somity.MVCRestAPI.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configures {
    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
