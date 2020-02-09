package com.han;

import java.util.Scanner;

public class Session {

    // list of questions to ask the user
    private Questions questions;
    // list of answers from the user
    private User user;

    // utility for reading user input
    protected static Scanner scan = new Scanner(System.in);

    public Session() {
        this.questions = new Questions();
        this.user = new User();
    }

    // initializes a session and returns a User with saved preferences
    public User initSession() {

        // loop the menu until choice 1 selected
        boolean endMenu = false;
        while(!endMenu) {
            displayMenu();
            int menuChoice = Session.scan.nextInt();

            // handle menu choices.
            switch(menuChoice) {
                case 0: // exit program
                    System.exit(0);

                case 1: // user chose to get a recommendation
                    questions.askQuestions();
                    endMenu = true; // exit menu
                    break;

                case 2: // update inventory with excel file and reset menu
                    Inventory.updateInventory();
                    break;

                default: // if choice was not valid, reset menu
            }
        }
        saveUserPreferences(user);
        return user;
    }


    // sets each preference of a given user object
    // value will be 0 for non-answered specifications
    public void saveUserPreferences(User user) {
        user.setType(questions.getType().getAnswer());
        user.setBrand(questions.getBrand().getAnswer());

        user.setWeightPref(questions.getWeight().getAnswer());
        user.setBalancePref(questions.getBalance().getAnswer());
        user.setStiffnessPref(questions.getStiffness().getAnswer());

        user.setSkillPref(questions.getSkill().getAnswer());
        user.setStylePref(questions.getStyle().getAnswer());
        user.setStrengthPref(questions.getStrength().getAnswer());

        user.setShaftPref(questions.getShaftDiameter().getAnswer());
    }


    // methods for displaying help text
    private void displayMenu() {
        System.out.println(
                "\nEnter a number from the following menu options:\n" +
                "0. Quit the program\n" +
                "1. Ask me questions to make a recommendation\n" +
                "2. Update inventory of racquets with input from excel file\n"
        );
    }


}
