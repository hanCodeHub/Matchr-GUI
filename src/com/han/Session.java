package com.han;


public class Session {

    // list of questions to ask the user
    private Questions questions;
    // list of answers from the user
    private User user;

    public Session() {
        this.questions = new Questions();
        this.user = new User();
    }

    // initializes a session and returns a User with saved preferences
    public User initSession() {
        questions.askQuestions();
        saveUserPreferences(user);
        return user;
    }

    // sets each preference of a given user object
    // value will be 0 for non-answered specifications
    public void saveUserPreferences(User user) {
        user.setBrand(questions.getBrand().getAnswer());

        user.setWeightPref(questions.getWeight().getAnswer());
        user.setBalancePref(questions.getBalance().getAnswer());
        user.setStiffnessPref(questions.getStiffness().getAnswer());

        user.setSkillPref(questions.getSkill().getAnswer());
        user.setStylePref(questions.getStyle().getAnswer());
        user.setStrengthPref(questions.getStrength().getAnswer());

        user.setShaftPref(questions.getShaftDiameter().getAnswer());
    }

}
