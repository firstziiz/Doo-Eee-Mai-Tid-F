package com.SubjectService;

import com.SubjectService.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${authenservice.jwt.secret}")
    private String jwtSecret;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor());
    }

    @Bean
    public MappedInterceptor tokenInterceptor() {
        return new MappedInterceptor(
                new String[] {"/subject_favorites/**", ""},
                new TokenInterceptor(jwtSecret));
    }
}
