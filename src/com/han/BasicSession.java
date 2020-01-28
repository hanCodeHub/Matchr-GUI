package com.han;


import java.util.List;

public class BasicSession extends Session {

    private Question style;
    private Question skill;
    private Question strength;

    // BasicSession initializes questions based on personal features
    public BasicSession() {
        this.style = new Question("style",
                "Enter a number between 1 – 5 for your style of play, " +
                        "where 1 is very defensive and 5 is very offensive.",
                1, 5
        );
        this.skill = new Question("skill",
                "Enter a number between 1 – 5 for your level of skill, " +
                        "where 1 is very beginner and 5 is very advanced.",
                1, 5
        );
        this.strength = new Question("strength",
                "Enter a number between 1 – 5 for your racquet arm strength, " +
                        "where 1 is very low and 5 is very high.",
                1, 5
        );
    }

    // returns a list of basic questions
    @Override
    public List<Question> getQuestions() {
        questions.add(style);
        questions.add(skill);
        questions.add(strength);
        return questions;
    }

}
