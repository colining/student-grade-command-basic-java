package com.tw;

class Student {
    private String name;
    private double sum;
    private double math;
    private double language;
    private double english;
    private double average;
    private double programming;
    private int id;

    String getName() {
        return name;
    }

    double getSum() {
        return sum;
    }

    double getMath() {
        return math;
    }

    double getLanguage() {
        return language;
    }

    double getEnglish() {
        return english;
    }

    double getProgramming() {
        return programming;
    }

    double getAverage() {
        return average;
    }


    Student(String name, int id, double math, double language, double english, double programming) {
        this.name = name;
        this.id = id;
        this.math = math;
        this.language = language;
        this.english = english;
        this.programming = programming;
        sum = math + language + english + programming;
        average = sum / 4.0;

    }
}
