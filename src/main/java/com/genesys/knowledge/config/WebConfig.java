package com.genesys.knowledge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by RomanH on 10.04.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.genesys.knowledge")
public class WebConfig extends WebMvcConfigurerAdapter {

    public WebConfig() {
        super();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/registration.html");
        //registry.addViewController("/registration.html").setViewName("registration");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    public static final int MAX_UPLOAD_SIZE = 50 * 1024 * 1024;
    public static final String SUPPORTED_MIME_TYPES = "application/pdf,text/html,image/gif,image/jpeg,image/pjpeg,image/png,image/svg+xml,image/tiff,image/vnd.microsoft.icon,image/vnd.wap.wbmp,image/webp,text/plain,text/xml,application/xml";

    @Bean
    CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
