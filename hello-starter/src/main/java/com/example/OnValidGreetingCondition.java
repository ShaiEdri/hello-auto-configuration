package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnValidGreetingCondition extends SpringBootCondition {
    private static final String PROPERTY_NAME = "hello.greeting";
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder condition = ConditionMessage.forCondition("ValidGreeting");
        Environment environment = context.getEnvironment();
        if (environment.containsProperty(PROPERTY_NAME)){
            String value = environment.getProperty(PROPERTY_NAME);
            if(Character.isUpperCase(value.charAt(0))){
                return ConditionOutcome.match(
                        condition.available(String.format("valid greeting %s", value))
                );
            }
            return ConditionOutcome.noMatch(
                    condition.because(String.format("Greeting %s , doesnt start with uppercase",value)));
        }
        return ConditionOutcome.noMatch(
                condition.because("Cannot find Greeting"));
    }
}

