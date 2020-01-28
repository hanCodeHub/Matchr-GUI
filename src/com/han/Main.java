package com.han;


public class Main {

    // a session displays questions and stores user preferences
    private static Session session;

    public static void main(String[] args) {

        // display and handle menu choices
        Session.displayMenu();
        int menuChoice = Session.scan.nextInt();

        if (menuChoice == 1) {
            session = new BasicSession();
        }
        else if (menuChoice == 2)
            session = new AdvancedSession();

        // confirm user understanding of specs
        if (session instanceof AdvancedSession) {
            // downcast for double check method
            if (!((AdvancedSession) session).doubleCheck()) {
                session = new BasicSession();
            }
        }

        // questions are asked based on the type of session
        session.askQuestions(session.getQuestions());

        session.displayPreferences(); // preferences displayed

        // calculate match between preferences and inventory in future release
    }

}
