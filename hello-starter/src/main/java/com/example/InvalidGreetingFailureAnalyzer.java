package com.example;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class InvalidGreetingFailureAnalyzer extends AbstractFailureAnalyzer<InvalidGreetingException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, InvalidGreetingException cause) {
        return new FailureAnalysis(
                "This is no good",
                "Fix this: " + cause.getGreeting(),
                rootFailure
        );
    }
}
