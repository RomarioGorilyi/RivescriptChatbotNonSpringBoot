package com.genesys.knowledge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by RomanH on 10.04.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.genesys.knowledge")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/registration.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    TemplateResolver thymeleafResolver;

    @Autowired
    SpringTemplateEngine thymeleafEngine;

    public static final int MAX_UPLOAD_SIZE = 50 * 1024 * 1024;
    public static final String SUPPORTED_MIME_TYPES = "application/pdf,text/html,image/gif,image/jpeg,image/pjpeg,image/png,image/svg+xml,image/tiff,image/vnd.microsoft.icon,image/vnd.wap.wbmp,image/webp,text/plain,text/xml,application/xml";

    @Bean
    CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login.html").setViewName("login");
//        //        registry.addViewController("/status").setViewName("status");
//    }

    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver
                = new org.thymeleaf.templateresolver.ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/view/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(thymeleafResolver);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver view = new ThymeleafViewResolver();
        view.setTemplateEngine(thymeleafEngine);
        view.setOrder(1);
        //        view.setViewNames(new String[]{"thymeleaf/*"});
        return view;
    }
}
