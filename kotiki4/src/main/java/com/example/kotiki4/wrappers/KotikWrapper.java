package com.example.kotiki4.wrappers;

import lombok.Data;

import java.time.LocalDate;

@Data
public class KotikWrapper {
    private final Integer id;
    private final String name;
    private final LocalDate birthday;
    private final String breedId;
    private final String colorId;
    private final Integer ownerId;
}
