package com.han;


import java.util.Scanner;

public class Question <T extends Comparable<T>> {

    private String name;  // question identifier
    private String question;  // question text
    private T low;  // lower limit for numeric answer
    private T high;  // upper limit for numeric answer

    private T answer;  // answer type is dependent on question

    // constructor for questions requesting a text input
    public Question(String name, String question) {
        this.name = name;
        this.question = question;
    }

    // constructor for questions requesting a numerical input
    public Question (String name, String question, T low, T high) {
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
    public T getLow() {
        return low;
    }
    public T getHigh() {
        return high;
    }
    public T getAnswer() {
        return answer;
    }

    // answer is set after question is asked
    public void setAnswer(T answer) {
        this.answer = answer;
    }


    // TEMP READING METHODS TO BE DELETED AFTER IMPLEMENTING UI
    // reads a text answer to a question from user
    public String readTxtAnswer() {
        Scanner scan = new Scanner(System.in);
        System.out.println(this.getQuestion());
        return scan.nextLine().strip();
    }

    // reads an int answer to a question from user
    public int readIntAnswer() {
        Scanner scan = new Scanner(System.in);
        System.out.println(this.getQuestion());
        return scan.nextInt();
    }

    // reads a validated float answer to a question from user
    public float readFloatAnswer() throws NumberFormatException {
        Scanner scan = new Scanner(System.in);
        System.out.println(this.getQuestion());
        return scan.nextFloat();
    }
    // END READING METHODS


    // returns true if the answer is one word with alphabetic chars
    public Boolean validateText(String input) {
        return input.matches("[a-zA-Z]+");
    }

    // validates the range of the answer is between low and high
    public Boolean isWithinRange(T input) {
        // if input is at the edge of low or high
        if (input.compareTo(getLow()) == 0 || input.compareTo(getHigh()) == 0)
            return true;

        // else check if input is between low and high
        return (input.compareTo(getLow()) > 0 && input.compareTo(getHigh()) < 0);
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                '}';
    }
}
