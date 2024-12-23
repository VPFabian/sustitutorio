package com.example.demo.config;

import com.example.demo.controller.ProductController;
import com.example.demo.security.apiKeyAuthFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServeConfig extends ResourceConfig {
    public ServeConfig(){
        register(apiKeyAuthFilter.class);
        register(ProductController.class);
    }
}
