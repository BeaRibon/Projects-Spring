package udemy.exer.exem1.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udemy.exer.exem1.model.v1.dto.GreetingDto;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public GreetingDto greetingDto(
            @RequestParam(value = "name", defaultValue = "Word")
            String name){
    return new GreetingDto(counter.incrementAndGet(), String.format(template, name));
    }
}