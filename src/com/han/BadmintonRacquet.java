package com.han;


public class BadmintonRacquet extends Racquet {

    // possible grip sizes for a badminton racquet
    public enum gripSize {G2, G3, G4, G5}

    private gripSize gripSize;
    private float shaftDiameter;

    // constructor
    public BadmintonRacquet(int id, String brand, String model) {
        super(id, brand, model);
    }

    // getters
    public float getShaftDiameter() {
        return shaftDiameter;
    }
    public BadmintonRacquet.gripSize getGripSize() {
        return gripSize;
    }


    // setters
    public void setShaftDiameter(float shaftDiameter) {
        this.shaftDiameter = shaftDiameter;
    }

    // gripSize can only be one of the constants in enum
    public void setGripSize(BadmintonRacquet.gripSize size) {
        this.gripSize = size;
    }
}
