package com.bee.carrental.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import io.swagger.v3.oas.models.OpenAPI;

import java.io.IOException;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        Resource resource = new ClassPathResource("openapi.yaml");
        Yaml yaml = new Yaml(new Constructor(OpenAPI.class));
        return yaml.load(resource.getInputStream());
    }
}
