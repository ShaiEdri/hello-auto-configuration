package com.example;

public class HelloConsoleImpl implements HelloService {
    private final String greeting;
    private final String ending;

    public HelloConsoleImpl(String greeting, String ending) {
        this.greeting = (greeting != null ? greeting : "hello");
        this.ending = (ending != null ? ending : "!!!");
        if (!Character.isUpperCase(this.greeting.charAt(0))){
            throw new InvalidGreetingException(this.greeting);
        }
    }
    public HelloConsoleImpl(){
        this(null, null);
    }
    @Override
    public void sayHello(String name) {
        String msg = String.format("%s %s%s", this.greeting, name, this.ending);
        System.out.println(msg);
    }
}
