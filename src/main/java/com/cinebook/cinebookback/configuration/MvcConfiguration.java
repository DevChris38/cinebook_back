package com.cinebook.cinebookback.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry)
    {
            registry.addViewController("/login").setViewName("login");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        }
    }
}
