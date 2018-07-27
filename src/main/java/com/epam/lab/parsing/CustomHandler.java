package com.epam.lab.parsing;


import com.epam.lab.model.Firearm;
import com.epam.lab.model.FirearmTTS;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomHandler extends DefaultHandler {

    private List<Firearm> firearmsList = null;
    private Firearm firearm = null;
    private FirearmTTS firearmTTS = null;

    List<Firearm> getFirearmsList() {
        return firearmsList;
    }

    boolean bHandy = false;
    boolean bOrigin = false;
    boolean bMaterials = false;
    boolean bCarry = false;
    boolean bSightRange = false;
    boolean bShackle = false;
    boolean bOptics = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("firearm")) {
            String model = attributes.getValue("model");

            firearm = new Firearm();
            firearm.setModel(String.format(model));
            if (firearmsList == null) {
                firearmsList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("handy")) {
            bHandy = true;
        } else if (qName.equalsIgnoreCase("origin")) {
            bOrigin = true;
        } else if (qName.equalsIgnoreCase("tts")) {
            firearmTTS = new FirearmTTS();
        } else if (qName.equalsIgnoreCase("carry")) {
            bCarry = true;
        } else if (qName.equalsIgnoreCase("sightRange")) {
            bSightRange = true;
        } else if (qName.equalsIgnoreCase("shackle")) {
            bShackle = true;
        } else if (qName.equalsIgnoreCase("optics")) {
            bOptics = true;
        } else if (qName.equalsIgnoreCase("materials")) {
            bMaterials = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("firearm")) {
            firearm.setTts(firearmTTS);
            firearmsList.add(firearm);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {

        if (bHandy) {
            firearm.setHandy(Integer.parseInt(new String(ch, start, length)));
            bHandy = false;
        } else if (bOrigin) {
            firearm.setOrigin(new String(ch, start, length));
            bOrigin = false;
        } else if (bCarry) {
            firearmTTS.setCarry(Integer.parseInt(new String(ch, start, length)));
            bCarry = false;
        } else if (bSightRange) {
            firearmTTS.setSightRange(Double.parseDouble(new String(ch, start, length)));
            bSightRange = false;
        } else if (bShackle) {
            firearmTTS.setShackle(Boolean.parseBoolean(new String(ch, start, length)));
            bShackle = false;
        } else if (bOptics) {
            firearmTTS.setOptics(Boolean.parseBoolean(new String(ch, start, length)));
            bOptics = false;
        } else if (bMaterials) {
            firearm.setMaterial(new String(ch, start, length));
            bMaterials = false;
        }
    }
}
