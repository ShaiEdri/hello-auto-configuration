package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({OutputCaptureExtension.class, SpringExtension.class})
class HelloAutoConfigurationTest {
    ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(HelloAutoConfiguration.class));

    @BeforeEach
    void setUp() {
    }

    @Test
    public void defaultServiceIsAutoConfigured(CapturedOutput output){
        contextRunner.run(context -> {
            assertEquals(1, context.getBeansOfType(HelloService.class).size());
            context.getBean(HelloService.class).sayHello("shai");
            assertTrue(output.getOut().toString().contains("hello shai!!!"));
        });
    }

    @Test
    public void defaultServiceBacksOff(CapturedOutput output){
        contextRunner
                .withUserConfiguration(UserConfig.class)
                .run(context -> {
                    assertEquals(1, context.getBeansOfType(HelloService.class));
                    context.getBean(HelloService.class).sayHello("works");
                    assertTrue(output.getOut().toString().contains("USER works***"));
        });
    }

    @Configuration
    static class UserConfig{
        @Bean
        public HelloService helloService(){
            return new HelloConsoleImpl("USER", "***");
        }
    }

}