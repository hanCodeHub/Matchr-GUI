package com.han;


public class Main {

    public static void main(String[] args) {

        // initializes new session and returns the user with saved preferences
        Session session = new Session();
        User user = session.initSession();

    }
}
