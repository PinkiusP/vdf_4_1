package com.company;

public enum Colors {
    colorReset("\u001B[0m"),
    blackText("\u001B[30m"),
    redText("\u001B[31m"),
    redBG("\u001B[41m"),
    greenText("\u001B[32m"),
    yellowText("\u001B[33m");

    private String color;

    Colors(String textColor) {
        this.color = textColor;
    }

    public String getColor() {
        return color;
    }
}