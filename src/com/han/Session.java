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
    public abstract List<Question> getQuestions();

    // returns a new session by having user select from menu options
    public static void initSession() {
        // track current session of the user
        Session session;

        while(true) {
            displayMenu();

            // handle menu choices
            int menuChoice = Session.scan.nextInt();
            // 0: update racquet inventory
            if (menuChoice == 0) {
                Inventory.getRacquetList();
            }
            // 1: init session with basic questions
            else if (menuChoice == 1) {
                session = new BasicSession();
                break;
            }
            // 2: init
            else if (menuChoice == 2) {
                session = new AdvancedSession();
                // downcast to confirm user selection of advanced option
                if (!((AdvancedSession) session).doubleCheck()) {
                    session = new BasicSession();
                }
                break;
            }
            // restart menu unless user enters 1 or 2
        }
        session.askQuestions(session.getQuestions());
        session.displayPreferences();

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
        System.out.println("Select a number from the following menu options:\n" +
                "0. Update inventory of racquets with input from excel file\n" +
                "1. Ask me basic questions based on my style of play\n" +
                "2. Ask me advanced questions based on racquet specifications\n");
    }
    private void displayPreferences() {
        System.out.println("Finding a match with your preferences: " +
                preferences.toString());
    }

}
