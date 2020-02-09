package com.han;

public class Questions {

    // questions for all racquets
    private Question<String> type;
    private Question<String> brand;
    private Question<Integer> style;
    private Question<Integer> skill;
    private Question<Integer> strength;
    private Question<Integer> weight;
    private Question<Integer> stiffness;
    private Question<Integer> balance;
    private Question<Float> shaftDiameter;


    // AdvancedSession initializes questions based on racquet specifications
    public Questions() {
        this.type = new Question<>("type",
                "What type of racquet are you looking for?");

        this.brand = new Question<>("brand",
                "Enter the name of your favorite brand");

        this.style = new Question<>("style",
                "Enter a number between 1 – 5 for your style of play, " +
                        "where 1 is very defensive and 5 is very offensive.",
                1, 5
        );
        this.skill = new Question<>("skill",
                "Enter a number between 1 – 5 for your level of skill, " +
                        "where 1 is very beginner and 5 is very advanced.",
                1, 5
        );
        this.strength = new Question<>("strength",
                "Enter a number between 1 – 5 for your racquet arm strength, " +
                        "where 1 is very low and 5 is very high.",
                1, 5
        );

        this.weight = new Question<>("weight",
                "Enter a number between 1 – 5 for your preferred weight, " +
                        "where 1 is lightest and 5 is heaviest",
                1, 5
        );
        this.stiffness = new Question<>("stiffness",
                "Enter a number between 1 – 10 for your preferred shaft stiffness, " +
                        "where 1 is softest and 10 is stiffest.",
                1, 10
        );
        this.balance = new Question<>("balance",
                "Enter a number between 1 – 10 for your preferred balance, " +
                        "where 1 is head lightest and 10 is head heaviest.",
                1, 10
        );

        this.shaftDiameter = new Question<>("shaftDiameter",
                "Enter a number in mm for your maximum preferred shaft diameter.");

    }

    // getters for Question objects
    public Question<String> getType() {
        return type;
    }
    public Question<String> getBrand() {
        return brand;
    }
    public Question<Integer> getStyle() {
        return style;
    }
    public Question<Integer> getSkill() {
        return skill;
    }
    public Question<Integer> getStrength() {
        return strength;
    }
    public Question<Integer> getWeight() {
        return weight;
    }
    public Question<Integer> getStiffness() {
        return stiffness;
    }
    public Question<Integer> getBalance() {
        return balance;
    }

    public Question<Float> getShaftDiameter() {
        return shaftDiameter;
    }

    // read and set answers to all general questions
    public void askQuestions() {
        type.setAnswer(type.readTxtAnswer());
        brand.setAnswer(brand.readTxtAnswer());

        style.setAnswer(style.readIntAnswer());
        skill.setAnswer(skill.readIntAnswer());
        strength.setAnswer(strength.readIntAnswer());

        weight.setAnswer(weight.readIntAnswer());
        stiffness.setAnswer(stiffness.readIntAnswer());
        balance.setAnswer(balance.readIntAnswer());
        shaftDiameter.setAnswer(shaftDiameter.readFloatAnswer());
    }

}
