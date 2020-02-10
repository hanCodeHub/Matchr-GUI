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
    private double matchIndex;

    // This constructor used when reading inventory file
    public Racquet(int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    // constructor for testing
    public Racquet(int id, String brand, String model,
                   int weight, int balance, int stiffness, float shaftDiameter,
                   int style, int skill, int strength) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.weight = weight;
        this.balance = balance;
        this.stiffness = stiffness;
        this.shaftDiameter = shaftDiameter;
        this.style = style;
        this.skill = skill;
        this.strength = strength;
    }

    // calculates the matchIndex as it relates to the user
    // returns the total difference of each value between Racquet and User
    public double calcMatchIndex(User user) {
        Number[] vals = new Number[7];
        double total = 0;

        // save the difference between each value
        // if user does not have preference, ignore the diff: set to 0
        int userPref = user.getBalancePref();
        vals[0] = userPref > 0 ? Math.abs(userPref - getBalance()) : 0;

        userPref = user.getWeightPref();
        vals[1] = userPref > 0 ? Math.abs(userPref - getWeight()) : 0;

        userPref = user.getStiffnessPref();
        vals[2] = userPref > 0 ? Math.abs(userPref - getStiffness()) : 0;

        userPref = user.getSkillPref();
        vals[3] = userPref > 0 ? Math.abs(userPref - getSkill()) : 0;

        userPref = user.getStrengthPref();
        vals[4] = userPref > 0 ? Math.abs(user.getStrengthPref() - getStrength()) : 0;

        userPref = user.getStylePref();
        vals[5] = userPref > 0 ? Math.abs(user.getStylePref() - getStyle()) : 0;

        float userShaftPref = user.getShaftPref();
        vals[6] = userShaftPref > 0 ? Math.abs(user.getShaftPref() - getShaftDiameter()) : 0;

        // adds up all the differences and returns the total
        for (Number val : vals) {
            total += val.doubleValue();
        }
        setMatchIndex(total);
        return total;
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

    public double getMatchIndex() {return matchIndex; }

    // setters
    public void setMatchIndex(double matchIndex) {
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
