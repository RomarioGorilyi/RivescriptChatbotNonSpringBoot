package com.genesys.knowledge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by RomanH on 13.04.2017.
 */
@Configuration
public class ThymeleafConfig {

    @Autowired
    TemplateResolver thymeleafResolver;

    @Autowired
    SpringTemplateEngine thymeleafEngine;

    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new org.thymeleaf.templateresolver.ServletContextTemplateResolver();
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
