package com.han;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Session {

    // PROTECTED ATTRIBUTES - USE IN CONCRETE CLASSES ONLY
    // list of questions to ask the user
    protected ArrayList<Question> questions = new ArrayList<>(3);
    // for storing user answers (int) to each question (String)
    protected HashMap<String, Integer> preferences = new HashMap<>(3);
    // utility for reading user input
    protected static Scanner scan = new Scanner(System.in);

    // obtains questions from concrete session classes
    protected abstract List<Question> getQuestions();

    // tracks current session
    private static Session session = null;


    // returns a new session by having user select from menu options
    public static void initSession() {
        // track current session of the user

        while(true) {
            displayMenu();

            // handle menu choices. menu is reset if handler returns true
            int menuChoice = Session.scan.nextInt();
            if (!handleChoices(menuChoice)) break;
        }

        // process user questions and display saved preferences
        session.askQuestions(session.getQuestions());
        session.displayPreferences();

        // MORE CODE ADDED LATER TO MATCH PREFERENCES TO RECOMMENDATION
        System.out.println("Searching for your ideal racquet...");
    }

    // handles the user menu choices based on the session above
    // returns boolean to check if menu should be reset
    private static Boolean handleChoices(int menuChoice) {

        boolean reset = false;

        switch(menuChoice) {
            case 0: // exit program
                System.exit(0);
            case 1: // user chose basic questions
                session = new BasicSession();
                break;
            case 2: // user chose advanced questions
                session = new AdvancedSession();
                // downcast to confirm user selection of advanced option
                if (!((AdvancedSession) session).doubleCheck())
                    session = new BasicSession();
                break;
            case 3: // update inventory with excel file and reset menu
                Inventory.updateInventory();
                reset = true;
                break;
            default: // if choice was not valid, reset menu
                reset = true;
        }
        return reset;
    }


    // asks the user each question in the list and returns answers as preferences
    private void askQuestions(List<Question> questions) {
        for (Question q : questions) {
            int answer = getAnswer(q);
            // each answer stored in preferences with corresponding question name
            preferences.put(q.getName(), answer);
        }
    }

    // get answer to a question from user
    private int getAnswer(Question question) {
        int userInput;

        // check that user value is within acceptable range of question
        do {
            System.out.println(question.getQuestion());
            userInput = scan.nextInt();
        } while (!question.isValidAnswer(userInput));

        return userInput;
    }


    // utility display methods
    private static void displayMenu() {
        System.out.println(
                "\nEnter a number from the following menu options:\n" +
                "0. Quit the program\n" +
                "1. Ask me basic questions based on personal preferences\n" +
                "2. Ask me advanced questions based on racquet specifications\n" +
                "3. Update inventory of racquets with input from excel file\n"
        );
    }

    private void displayPreferences() {
        System.out.println("Finding a match with your preferences: " +
                preferences.toString());
    }

}
