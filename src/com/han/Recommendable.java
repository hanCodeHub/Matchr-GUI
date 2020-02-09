package com.han;

public interface Recommendable {

    // A recommendable item must be able to suit a User's preferred:
    // brand: name of the manufacturer
    // type: name of the specialization
    // matchIndex: a way of calculating its similarity to the user

    String getBrand();
    String getType();

    int getMatchIndex();
    double calcMatchIndex(User user);
}
