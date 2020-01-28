package com.han;

public class Racquet {

    // basic attributes
    private int style;      // 1 - 5
    private int skill;      // 1 - 5
    private int strength;   // 1 - 5

    // advanced attributes
    private int weight;     // 1 - 5
    private int balance;    // 1 - 10
    private int stiffness;  // 1 - 10

    // presented information
    private String brand;
    private String model;
    private String description; // optional

    // Racquet attributes are never altered after initialization
    public Racquet(int style, int skill, int strength, int weight, int balance, int stiffness,
            String brand, String model, String description) {
        this.style = style;
        this.skill = skill;
        this.strength = strength;
        this.weight = weight;
        this.balance = balance;
        this.stiffness = stiffness;
        this.brand = brand;
        this.model = model;
        this.description = description;
    }

    // getters
    public int getStyle() {
        return style;
    }
    public int getSkill() {
        return skill;
    }
    public int getStrength() {
        return strength;
    }
    public int getWeight() {
        return weight;
    }
    public int getBalance() {
        return balance;
    }
    public int getStiffness() {
        return stiffness;
    }

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getDescription() {
        return description;
    }

    // description can be optionally added later
    public void setDescription(String description) {
        this.description = description;
    }
}
