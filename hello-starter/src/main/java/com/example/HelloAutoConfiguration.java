package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnValidGreeting
    public HelloService helloService(HelloProperties helloProperties) {
        return new HelloConsoleImpl(
                helloProperties.getGreeting(),
                helloProperties.getEnding()
        );
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService2() {
        return new HelloConsoleImpl();
    }

}
