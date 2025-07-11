package com.transactionchallenge.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping()
    public ResponseEntity<Object> health() {
        return ResponseEntity.ok().body("Server is running!");
    }

}
