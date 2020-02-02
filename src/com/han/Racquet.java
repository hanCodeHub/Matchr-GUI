package com.han;

public class Racquet {
    // unique identifier and info
    private int id;
    private String brand;
    private String model;

    // advanced attributes
    private int weight;     // 1 - 5
    private int balance;    // 1 - 10
    private int stiffness;  // 1 - 10

    // basic attributes
    private int style;      // 1 - 5
    private int skill;      // 1 - 5
    private int strength;   // 1 - 5

    // default constructor used when reading from inventory file
    public Racquet() {}

    public Racquet(int id, String brand, String model,
                   int weight, int balance, int stiffness,
                   int style, int skill, int strength) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.weight = weight;
        this.balance = balance;
        this.stiffness = stiffness;
        this.style = style;
        this.skill = skill;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return
                "id = " + id + " | brand = " + brand + " | model = " + model +"\n";
    }
}
