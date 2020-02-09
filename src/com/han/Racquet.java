package com.han;


public class Racquet implements Recommendable {

    // unique identifier and info
    private int id;
    private String brand;
    private String model;

    // advanced attributes
    private int weight;     // 1 - 5
    private int balance;    // 1 - 10
    private int stiffness;  // 1 - 10
    private float shaftDiameter;  // max 9.0mm

    // basic attributes
    private int style;      // 1 - 5
    private int skill;      // 1 - 5
    private int strength;   // 1 - 5

    // value for recommendation (lower is closer to user)
    private int matchIndex;

    // This constructor used when reading inventory file
    public Racquet(int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
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
    public float getShaftDiameter() { return shaftDiameter;}

    public int getMatchIndex() {return matchIndex; }

    // setters
    public void setMatchIndex(int matchIndex) {
        this.matchIndex = matchIndex;
    }

    // validateAttr ensures that attributes of new Racquets are between low and high
    // if invalid, an error is thrown and handled by calling method
    private <T extends Comparable<T>> boolean validateAttr(T attribute, T low, T high)
        throws IllegalArgumentException {
        if (attribute.compareTo(low) > 0 && attribute.compareTo(high) < 0)
            return true;
        else throw new IllegalArgumentException("provided value is out of range");
    }

    public void setWeight(int weight) {
        if (validateAttr(weight, 0, 6))
            this.weight = weight;
    }

    public void setBalance(int balance) {
        if (validateAttr(balance, 0, 11))
            this.balance = balance;
    }

    public void setStiffness(int stiffness) {
        if (validateAttr(stiffness, 0, 11))
            this.stiffness = stiffness;
    }

    public void setStyle(int style) {
        if (validateAttr(style, 0, 6))
            this.style = style;
    }

    public void setSkill(int skill) {
        if (validateAttr(skill, 0, 6))
            this.skill = skill;
    }

    public void setStrength(int strength) {
        if (validateAttr(strength, 0, 6))
            this.strength = strength;
    }

    public void setShaftDiameter(float diameter) {
        if (validateAttr(diameter, 0.0F, 9.0F))
            this.shaftDiameter = diameter;
    }


    @Override
    public String toString() {
        return
                "id = " + id + " | brand = " + brand + " | model = " + model +"\n";
    }
}
