package com.han;

public interface Recommendable {

    // A recommendable item must be able to suit a User's preferred:
    // brand: name of the manufacturer
    // matchIndex: a way of calculating its similarity to the user

    String getBrand();
    double getMatchIndex();
    double calcMatchIndex(User user);
}
