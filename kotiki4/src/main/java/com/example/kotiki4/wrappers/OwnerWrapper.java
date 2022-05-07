package com.example.kotiki4.wrappers;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OwnerWrapper {
    private final Integer id;
    private final String name;
    private final LocalDate birthday;
}
