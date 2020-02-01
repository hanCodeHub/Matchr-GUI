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

    // getters
    public int getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
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
    public int getStyle() {
        return style;
    }
    public int getSkill() {
        return skill;
    }
    public int getStrength() {
        return strength;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void setStiffness(int stiffness) {
        this.stiffness = stiffness;
    }
    public void setStyle(int style) {
        this.style = style;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

}
