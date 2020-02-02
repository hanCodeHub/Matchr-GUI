package com.han;

import java.util.List;

public class AdvancedSession extends Session {

    private Question weight;
    private Question stiffness;
    private Question balance;

    // AdvancedSession initializes questions based on racquet specifications
    public AdvancedSession() {
        this.weight = new Question("weight",
                "Enter a number between 1 – 5 for your preferred weight, " +
                        "where 1 is lightest and 5 is heaviest",
                1, 5
        );
        this.stiffness = new Question("stiffness",
                "Enter a number between 1 – 10 for your preferred shaft stiffness, " +
                        "where 1 is softest and 10 is stiffest.",
                1, 10
        );
        this.balance = new Question("balance",
                "Enter a number between 1 – 10 for your preferred balance, " +
                        "where 1 is head lightest and 10 is head heaviest.",
                1, 10
        );
    }

    // returns a list of advanced questions
    @Override
    public List<Question> getQuestions() {
        questions.add(weight);
        questions.add(stiffness);
        questions.add(balance);
        return questions;
    }

    public Boolean doubleCheck() {
        System.out.println("Do you understand how racquet specifications work?\n" +
                "Enter y if you wish to proceed.\n" +
                "Enter anything else to proceed with basic questions.");
        // any answer other than 'y' should default to false
        return scan.next().equals("y");
    }
}
