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
    public int getMatchIndex() {return matchIndex; }

    // setters
    public void setMatchIndex(int matchIndex) {
        this.matchIndex = matchIndex;
    }

    // attribute setters ensure that specifications of new Racquets are within valid range
    // if invalid, an error is thrown and handled by calling method
    public void setWeight(int weight) {
        if (weight >= 1 && weight <= 5)
            this.weight = weight;
        else throw new IllegalArgumentException("Provided weight is out of range for " +
                this.getBrand() + " " + this.getModel());
    }

    public void setBalance(int balance) {
        if (balance >= 1 && balance <= 10)
            this.balance = balance;
        else throw new IllegalArgumentException("Provided balance is out of range for " +
                this.getBrand() + " " + this.getModel());
    }

    public void setStiffness(int stiffness) {
        if (stiffness >= 1 && stiffness <= 10)
            this.stiffness = stiffness;
        else throw new IllegalArgumentException("Provided stiffness is out of range for " +
                this.getBrand() + " " + this.getModel());
    }

    public void setStyle(int style) {
        if (style >= 1 && style <= 5)
            this.style = style;
        else throw new IllegalArgumentException("Provided style is out of range for " +
                this.getBrand() + " " + this.getModel());
    }

    public void setSkill(int skill) {
        if (skill >= 1 && skill <= 5)
            this.skill = skill;
        else throw new IllegalArgumentException("Provided skill is out of range for " +
                this.getBrand() + " " + this.getModel());
    }

    public void setStrength(int strength) {
        if (strength >= 1 && strength <= 5)
            this.strength = strength;
        else throw new IllegalArgumentException("Provided strength is out of range for " +
                this.getBrand() + " " + this.getModel());
    }


    @Override
    public String toString() {
        return
                "id = " + id + " | brand = " + brand + " | model = " + model +"\n";
    }
}
