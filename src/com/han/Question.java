package com.han;


import java.util.Scanner;

public class Question <T> {

    private String name;  // question identifier
    private String question;  // question text
    private int low;  // lower limit for numeric answer
    private int high;  // upper limit for numeric answer

    private T answer;  // answer type is dependent on question

    // constructor for questions requesting a text input
    public Question(String name, String question) {
        this.name = name;
        this.question = question;
    }

    // constructor for questions requesting a numerical input
    public Question (String name, String question, int low, int high) {
        this(name, question);
        this.low = low;
        this.high = high;
    }

    // getters
    public String getName() {
        return name;
    }
    public String getQuestion() {
        return question;
    }
    public int getLow() {
        return low;
    }
    public int getHigh() {
        return high;
    }
    public T getAnswer() {
        return answer;
    }

    // answer is set after question is asked
    public void setAnswer(T answer) {
        this.answer = answer;
    }


    // READING METHODS TO BE DELETED AFTER IMPLEMENTING UI

    // reads a validated text answer to a question from user
    public String readTxtAnswer() {

        while (true) {
            // ask question
            Scanner scan = new Scanner(System.in);
            System.out.println(this.getQuestion());
            String userInput = scan.nextLine().strip();

            // if expected answer is numeric - check within range
            if (isValidText(userInput))
                return userInput;
        }

    }

    // reads a validated int answer to a question from user
    public int readIntAnswer() {

        while (true) {
            // ask question
            Scanner scan = new Scanner(System.in);
            System.out.println(this.getQuestion());
            String userInput = scan.next();

            // if expected answer is numeric - check within range
            if (isValidNum(userInput)) {
                return Integer.parseInt(userInput);
            }
        }
    }

    // reads a validated float answer to a question from user
    public float readFloatAnswer() throws NumberFormatException {
        // ask question
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println(this.getQuestion());
                return scan.nextFloat();
            } catch (NumberFormatException e) {
                System.out.println("Answer must be a float number. Try again.");
            }
        }

    }


    // returns true if the answer is one word with alphabetic chars
    private Boolean isValidText(String input) {
        return input.matches("[a-zA-Z]+");
    }

    // returns true if the answer is within valid range and can be converted to int
    private Boolean isValidNum(String input) throws NumberFormatException {
        int answer = 0;  // 0 will not pass. valid answer must be assigned

        try {  // check conversion to Integer
            answer = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Cannot validate answer. User provided String instead of Integer");
        }
        // return validation
        return (answer >= this.getLow() && answer <= this.getHigh());
    }


    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                '}';
    }
}
