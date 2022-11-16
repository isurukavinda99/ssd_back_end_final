package com.ssd.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    private static String GET = "GET";
    private static String POST = "POST";
    private static String DELETE = "DELETE";
    private static String PUT = "PUT";

    // config cors and headers
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedMethods(GET,POST,PUT,DELETE)
                       .allowedHeaders("*")
                       .allowedOrigins("*")
                       .allowCredentials(true);
            }
        };
    }

}
