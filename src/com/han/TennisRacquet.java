package com.han;


public class TennisRacquet extends Racquet {

    // possible grip sizes for a tennis racquet (European notation)
    public enum gripSize {G0, G1, G2, G3, G4, G5, G6}

    private gripSize gripSize;
    private float frameThickness;

    // constructor
    public TennisRacquet(int id, String brand, String model) {
        super(id, brand, model);
    }

    // getters
    public float getFrameThickness() {
        return frameThickness;
    }
    public TennisRacquet.gripSize getGripSize() {
        return gripSize;
    }

    // setters
    public void setFrameThickness(float frameThickness) {
        this.frameThickness = frameThickness;
    }

    // gripSize can only be one of the constants in enum
    public void setGripSize(TennisRacquet.gripSize size) {
        this.gripSize = size;
    }
}
