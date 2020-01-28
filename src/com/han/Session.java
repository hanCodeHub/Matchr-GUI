package com.han;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Session {

    // list of questions to ask the user
    protected ArrayList<Question> questions = new ArrayList<>(3);
    // for storing user answers (int) to each question (String)
    protected HashMap<String, Integer> preferences = new HashMap<>(3);

    // utility for reading user input
    public static Scanner scan = new Scanner(System.in);

    // obtains questions from concrete session classes
    public abstract List<Question> getQuestions();

    // asks the user each question in the list and returns answers as preferences
    public HashMap<String, Integer> askQuestions(List<Question> questions) {
        for (Question q : questions) {
            int answer = getAnswer(q);
            // each answer stored in preferences with corresponding question name
            preferences.put(q.getName(), answer);
        }
        return preferences;
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
    public static void displayMenu() {
        System.out.println("Select a number from the following menu options:\n" +
                "1. Ask me basic questions based on my style of play\n" +
                "2. Ask me advanced questions based on racquet specifications\n");
    }
    public void displayPreferences() {
        System.out.println("Finding a match with your preferences: " +
                preferences.toString());
    }

}
