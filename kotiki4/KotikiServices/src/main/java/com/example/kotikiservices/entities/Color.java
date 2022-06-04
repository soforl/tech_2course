package com.example.kotikiservices.entities;

public enum Color {
    Black("black"),
    White("white"),
    Red("red"),
    Grey("grey"),
    Multi("multi");

    public String text;

    private Color(String text) {
        this.text = text;
    }



}