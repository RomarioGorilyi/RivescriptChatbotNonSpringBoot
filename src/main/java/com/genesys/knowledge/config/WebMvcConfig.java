package com.genesys.knowledge.config;

import com.genesys.knowledge.controller.RsController;
import com.genesys.knowledge.service.knowledge.KnowledgeService;
import com.genesys.knowledge.service.rivescript.RsServicePool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by RomanH on 10.04.2017.
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/registration.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Bean
    public RsController rsController() {
        return new RsController();
    }

    @Bean
    public KnowledgeService knowledgeService() {
        return new KnowledgeService();
    }
}
