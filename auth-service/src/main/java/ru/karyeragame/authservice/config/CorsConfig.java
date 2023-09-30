package ru.karyeragame.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

public class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfig = new CorsConfiguration();
        //corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8080"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "HEAD", "PUT", "PATCH"));
        corsConfig.addAllowedHeader("Access-Control-Allow-Origin");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

}
