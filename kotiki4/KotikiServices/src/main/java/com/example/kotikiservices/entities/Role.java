package com.example.kotikiservices.entities;

public enum Role {
    admin("admin"),
    user("user");

    public String text;

    private Role(String text){
        this.text=text;
    }
}
