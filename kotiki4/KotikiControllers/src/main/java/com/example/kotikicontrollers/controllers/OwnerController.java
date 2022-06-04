package com.example.kotikicontrollers.controllers;

import com.example.kotikicontrollers.wrappers.KotikWrapper;
import com.example.kotikicontrollers.wrappers.OwnerWrapper;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class OwnerController {
    @Autowired
    private KafkaTemplate<Long, String> kafka;

    @PostMapping(value = "addOwner")
    public ResponseEntity addOwner(@RequestBody OwnerWrapper ownerWrapper) {
        try {
            var json = new GsonBuilder().create().toJson(ownerWrapper, OwnerWrapper.class);
            kafka.send("owners.addOwner", json);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body("You're doing great, bro");
    }
}
