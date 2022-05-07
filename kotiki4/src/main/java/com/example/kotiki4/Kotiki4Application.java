package com.example.kotiki4;

import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.services.KotikService;
import com.example.kotiki4.services.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Kotiki4Application {

    public static void main(String[] args) {
        SpringApplication.run(Kotiki4Application.class, args);
    }

    @Bean
    CommandLineRunner run(OwnerService ownerService, KotikService kotikService){
        return args -> {
            Owner owner = new Owner("Artem", LocalDate.of(2000, 2, 2), "admin", "asdf", "asdfg");
            ownerService.saveOwner(owner);
            Kotik kotik1 = new Kotik("Koshka", LocalDate.of(2020, 3, 20), "Black", "Britanskaya", owner);
            kotikService.saveKotik(kotik1);
            Kotik kotik2 = new Kotik("Kot", LocalDate.of(2020, 3, 20), "Grey", "Britanskaya", owner);
            kotikService.saveKotik(kotik2);
            Owner owner2 = new Owner("Kolya", LocalDate.of(2001, 10, 5), "user", "qwer", "qwert");
            ownerService.saveOwner(owner2);
            Kotik kotik3 = new Kotik("Koteechka", LocalDate.of(2020, 3, 20), "White", "Cheshirskiy", owner2);
            kotikService.saveKotik(kotik3);
        };
    }

}
