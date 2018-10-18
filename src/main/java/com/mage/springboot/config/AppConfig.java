package com.mage.springboot.config;

import com.mage.springboot.filter.SpringBootFilter;
import com.mage.springboot.interceptor.LoginHandlerInterceptor;
import com.mage.springboot.listener.SpringBootListener;
import com.mage.springboot.resolver.LocalResolver;
import com.mage.springboot.servlet.SpringBootServlet;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration//指名这是一个配置类，用来添加组件的
public class AppConfig implements WebMvcConfigurer {

    //    @Bean
    //    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
    //        return new EmbeddedServletContainerCustomizer() {
    //            @Override
    //            public void customize(ConfigurableEmbeddedServletContainer container) {
    //                container.setSessionTimeout(1, TimeUnit.MINUTES);
    //            }
    //        };
    //    }

    /**
     * 注册servlet组件
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new SpringBootServlet(), "/servlet");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SpringBootFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myfilter"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new SpringBootListener());
    }

    /**
     * 配置servlet容器服务器
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(8000);
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/", "/index.html", "/usr/login")
                        .excludePathPatterns("/asserts/**", "/webjars/**");

            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new LocalResolver();
    }
}
