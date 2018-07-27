package com.epam.lab.model;


public class Firearm {

    private String model;
    private String origin;
    private FirearmTTS tts;
    private int handy;
    private String material;

    public Firearm(){}

    public Firearm(String model, String origin, FirearmTTS tts, int handy, String material) {
        this.model = model;
        this.handy = handy;
        this.origin = origin;
        this.tts = tts;
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHandy(int handy) {
        this.handy = handy;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public FirearmTTS getTts() {
        return tts;
    }

    public void setTts(FirearmTTS tts) {
        this.tts = tts;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Firearm{" +
                "model='" + model + '\'' +
                ", handy=" + handy +
                ", origin='" + origin + '\'' +
                ", tts=" + tts +
                ", material='" + material + '\'' +
                '}';
    }
}
