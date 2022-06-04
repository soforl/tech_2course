package com.example.kotikicontrollers.wrappers;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OwnerWrapper {
    private final Integer id;
    private final String name;
    private final LocalDate birthday;
    private final String role;
    private final String username;
    private final String password;
}
