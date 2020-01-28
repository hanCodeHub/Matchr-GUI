package com.han;


public class Question {
    private String name;
    private String question;
    private int low;  // lower limit for answer
    private int high;  // upper limit for answer

    public Question(String name, String question, int low, int high) {
        this.name = name;
        this.question = question;
        this.low = low;
        this.high = high;
    }

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

    // returns true if the provided answer is within valid range
    public Boolean isValidAnswer(int answer) {
        return (answer >= this.getLow() && answer <= this.getHigh());
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                '}';
    }
}
