package Matchr_App;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "racquet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Racquet implements Recommendable, Comparable<Racquet> {

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

    public Racquet() {
    }

    // This constructor used when reading inventory file
    public Racquet(int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    // constructor for testing
    public Racquet(int id, String brand, String model,
                   int weight, int balance, int stiffness,
                   int style, int skill, int strength,
                   float shaftDiameter) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.weight = weight;
        this.balance = balance;
        this.stiffness = stiffness;
        this.style = style;
        this.skill = skill;
        this.strength = strength;
        this.shaftDiameter = shaftDiameter;
    }

    // calculates the matchIndex as it relates to the user
    // formula: total difference of each value between Racquet and User
    public double calcMatchIndex(User user) {
        Number[] vals = new Number[7];

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

        // adds up all the differences to total
        double total = Arrays.stream(vals)
                .map(Number::doubleValue)
                .reduce(Double::sum)
                .get();

        // save matchIndex and return it
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

    public void setId(int id) {
        this.id = id;
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
    public void setShaftDiameter(float diameter) {
        this.shaftDiameter = diameter;
    }


    // validateAttr ensures that attributes of new Racquets are between low and high
    // if invalid, the attribute will not be set by the setter
    public <T extends Comparable<T>> boolean validateAttr(T attribute, T low, T high)
            throws IllegalArgumentException {

        // if attribute is at the edge of low or high
        if (attribute.compareTo(low) == 0 || attribute.compareTo(high) == 0)
            return true;

            // if attribute is between low and high
        else return attribute.compareTo(low) > 0 && attribute.compareTo(high) < 0;
    }


    @Override
    public String toString() {
        return
                "id = " + id + " | brand = " + brand + " | model = " + model +"\n";
    }

    // Racquets are sorted by id
    @Override
    public int compareTo(Racquet o) {
        return Integer.compare(this.getId(), o.getId());
    }

    // Racquets are verified by id, brand, model
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racquet racquet = (Racquet) o;
        return id == racquet.id &&
                Objects.equals(brand, racquet.brand) &&
                Objects.equals(model, racquet.model);
    }

}
