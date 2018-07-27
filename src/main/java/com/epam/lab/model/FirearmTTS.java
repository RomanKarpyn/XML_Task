package com.epam.lab.model;

import java.util.Objects;

public class FirearmTTS {
    private int carry;
    private double sightRange;
    private boolean isShackle;
    private boolean isOptics;

    public FirearmTTS() {
    }

    public FirearmTTS( int carry, double sightRange, boolean isShackle, boolean isOptics) {
        this.carry = carry;
        this.sightRange = sightRange;
        this.isShackle = isShackle;
        this.isOptics = isOptics;
    }

    public void setCarry(int carry) {
        this.carry = carry;
    }

    public void setSightRange(double sightRange) {
        this.sightRange = sightRange;
    }

    public void setShackle(boolean shackle) {
        isShackle = shackle;
    }

    public void setOptics(boolean optics) {
        isOptics = optics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirearmTTS that = (FirearmTTS) o;
        return carry == that.carry &&
                Double.compare(that.sightRange, sightRange) == 0 &&
                isShackle == that.isShackle &&
                isOptics == that.isOptics;
    }

    @Override
    public int hashCode() {

        return Objects.hash(carry, sightRange, isShackle, isOptics);
    }

    @Override
    public String toString() {
        return "FirearmTTS{" +
                "carry=" + carry +
                ", sightRange=" + sightRange +
                ", isShackle=" + isShackle +
                ", isOptics=" + isOptics +
                '}';
    }
}
