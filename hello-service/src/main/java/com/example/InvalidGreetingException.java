package com.example;

public class InvalidGreetingException extends RuntimeException {
    private final String greeting;
    public InvalidGreetingException(String greeting) {
        super("Invalid greeting : " + greeting);
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
