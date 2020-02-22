package Matchr_App;


// holds user info and preferences
public class User {

    // text preferences
    private String brand;  // preferred brand

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

    // preferred racquet - saved by user on UI
    private Racquet racquet;

    // default constructor used when reading input
    public User() {}

    // constructor for testing with prebuilt preferences
    public User(String brand,
                int weightPref, int balancePref, int stiffnessPref,
                int stylePref, int skillPref, int strengthPref, float shaftPref) {
        this.brand = brand;
        this.weightPref = weightPref;
        this.balancePref = balancePref;
        this.stiffnessPref = stiffnessPref;
        this.stylePref = stylePref;
        this.skillPref = skillPref;
        this.strengthPref = strengthPref;
        this.shaftPref = shaftPref;
    }

    // getters
    public String getBrand() {
        return brand;
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
    public Racquet getRacquet() {
        return racquet;
    }

    // setters
    public void setBrand(String brand) {
        this.brand = brand;
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
    public void setRacquet(Racquet racquet) {
        this.racquet = racquet;
    }

    @Override
    public String toString() {
        return "User preferred values:" +
                "\nbrand = " + brand +
                "\nweight = " + weightPref +
                "\nbalance = " + balancePref +
                "\nstiffness = " + stiffnessPref +
                "\nstyle = " + stylePref +
                "\nskill = " + skillPref +
                "\nstrength = " + strengthPref +
                "\nshaft = " + shaftPref;
    }
}
