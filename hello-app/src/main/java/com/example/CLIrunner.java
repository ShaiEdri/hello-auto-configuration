package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLIrunner implements CommandLineRunner {
    private final HelloService helloService;

    public CLIrunner(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void run(String... args) throws Exception {
        helloService.sayHello("world");
    }
}
