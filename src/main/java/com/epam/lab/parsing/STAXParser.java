package com.epam.lab.parsing;


import com.epam.lab.model.Firearm;
import com.epam.lab.model.FirearmTTS;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class STAXParser implements Parser {

    private static final Logger LOG = Logger.getLogger(STAXParser.class);

    @Override
    public List<Firearm> parseDoc() {
        Firearm firearm = null;
        List<Firearm> firearms = new ArrayList<>();
        FirearmTTS firearmTTS = null;

        boolean bOrigin = false;
        boolean bHandy = false;
        boolean bMaterials = false;
        boolean bCarry = false;
        boolean bSightRange = false;
        boolean bShackle = false;
        boolean bOptics = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = null;
        try {
            eventReader = factory.createXMLEventReader(
                    new FileReader("src/firearm.xml"));
        } catch (XMLStreamException | FileNotFoundException e1) {
            e1.printStackTrace();
        }
        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    if (qName.equalsIgnoreCase("firearm")) {
                        firearm = new Firearm();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        String model = attributes.next().getValue();
                        firearm.setModel(String.format(model));
                    } else if (qName.equalsIgnoreCase("origin")) {
                        bOrigin = true;
                    } else if (qName.equalsIgnoreCase("tts")) {
                        firearmTTS = new FirearmTTS();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                    } else if (qName.equalsIgnoreCase("carry")) {
                        bCarry = true;
                    } else if (qName.equalsIgnoreCase("sightRange")) {
                        bSightRange = true;
                    } else if (qName.equalsIgnoreCase("shackle")) {
                        bShackle = true;
                    } else if (qName.equalsIgnoreCase("optics")) {
                        bOptics = true;
                    } else if (qName.equalsIgnoreCase("handy")) {
                        bHandy = true;

                    } else if (qName.equalsIgnoreCase("materials")) {
                        bMaterials = true;
                    }
                break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (bOrigin) {
                        firearm.setOrigin(characters.getData());
                        bOrigin = false;
                    }
                    if (bCarry) {
                        firearmTTS.setCarry(Integer.parseInt(characters.getData()));
                        bCarry = false;
                    }
                    if (bSightRange) {
                        firearmTTS.setSightRange(Double.parseDouble(characters.getData()));
                        bSightRange = false;
                    }
                    if (bShackle) {
                        firearmTTS.setShackle(Boolean.parseBoolean(characters.getData()));
                        bShackle = false;
                    }
                    if (bOptics) {
                        firearmTTS.setOptics(Boolean.parseBoolean(characters.getData()));
                        bOptics = false;
                    }
                    if (bHandy) {
                        firearm.setHandy(Integer.parseInt(characters.getData()));
                        bHandy = false;
                    }
                    if (bMaterials) {
                        firearm.setMaterial(characters.getData());
                        bMaterials = false;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("firearm")) {
                        firearm.setTts(firearmTTS);
                        firearms.add(firearm);
                    }
                    break;
            }
        }
        firearms.sort(Comparator.comparing(Firearm::getModel));
        for (Firearm obj : firearms) {
            LOG.info(obj);
        }
        return firearms;
    }
}
