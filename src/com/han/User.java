package com.han;


// holds user info and preferences
public class User {

    // text preferences
    private String brand;  // preferred brand
    private String type;  // preferred racquet type

    // advanced attributes
    private int weightPref;     // 1 - 5
    private int balancePref;    // 1 - 10
    private int stiffnessPref;  // 1 - 10

    // basic attributes
    private int stylePref;      // 1 - 5
    private int skillPref;      // 1 - 5
    private int strengthPref;   // 1 - 5

    // special attributes
    private float shaftPref;  // badminton racquets

    // Only default constructor used

    // getters
    public String getBrand() {
        return brand;
    }
    public String getType() {
        return type;
    }
    public int getWeightPref() {
        return weightPref;
    }
    public int getBalancePref() {
        return balancePref;
    }
    public int getStiffnessPref() {
        return stiffnessPref;
    }
    public int getStylePref() {
        return stylePref;
    }
    public int getSkillPref() {
        return skillPref;
    }
    public int getStrengthPref() {
        return strengthPref;
    }
    public float getShaftPref() {
        return shaftPref;
    }

    // setters
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setWeightPref(int weightPref) {
        this.weightPref = weightPref;
    }
    public void setBalancePref(int balancePref) {
        this.balancePref = balancePref;
    }
    public void setStiffnessPref(int stiffnessPref) {
        this.stiffnessPref = stiffnessPref;
    }
    public void setStylePref(int stylePref) {
        this.stylePref = stylePref;
    }
    public void setSkillPref(int skillPref) {
        this.skillPref = skillPref;
    }
    public void setStrengthPref(int strengthPref) {
        this.strengthPref = strengthPref;
    }
    public void setShaftPref(float shaftPref) {
        this.shaftPref = shaftPref;
    }

    @Override
    public String toString() {
        return "User preferred values:" +
                "\nbrand = " + brand +
                "\ntype = " + type +
                "\nweight = " + weightPref +
                "\nbalancePref = " + balancePref +
                "\nstiffnessPref = " + stiffnessPref +
                "\nstylePref = " + stylePref +
                "\nskillPref = " + skillPref +
                "\nstrengthPref = " + strengthPref +
                "\nshaftPref = " + shaftPref;
    }
}
