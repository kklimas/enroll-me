package com.server.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from api");
    }

    @GetMapping("/goodbye")
    public ResponseEntity<String> sayGoodbye() {
        return ResponseEntity.ok("Good bye");
    }

}
