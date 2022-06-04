package com.example.kotikicontrollers.controllers;


import com.example.kotikicontrollers.wrappers.KotikWrapper;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

// http://localhost:8081/kotiki/

@RestController
@RequestMapping
public class KotikController {

    @Autowired
    private KafkaTemplate<Long, String> kafka;

    @PostMapping(value = "addKotik")
    public ResponseEntity addKotik(@RequestBody KotikWrapper kotikWrapper) {
        try {
            var json = new GsonBuilder().create().toJson(kotikWrapper, KotikWrapper.class);
            kafka.send("kotiki.addKotik", json);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body("You're doing great, bro");
    }
}
