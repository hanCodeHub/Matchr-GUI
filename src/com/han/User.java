package com.han;

import java.util.HashMap;

// holds user info and preferences
public class User {

    // advanced preferences
    private int weightPref;     // 1 - 5
    private int balancePref;    // 1 - 10
    private int stiffnessPref;  // 1 - 10

    // basic attributes
    private int stylePref;      // 1 - 5
    private int skillPref;      // 1 - 5
    private int strengthPref;   // 1 - 5

    // preferences contains user answers obtained from Session
    private HashMap<String, Integer> preferences;

    // getters
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


    // sets the correct preference by switching on the name
    private void setPreference(String name, int value) {
        // user may have any combination of preferences selected
        switch (name) {
            
        }
    }

    // setters
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
}
