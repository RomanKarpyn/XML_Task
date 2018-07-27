package com.epam.lab.parsing;

import com.epam.lab.model.Firearm;
import com.epam.lab.model.FirearmTTS;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DOMParser implements Parser {

    private static final Logger LOG = Logger.getLogger(DOMParser.class);

    @Override
    public List<Firearm> parseDoc() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Integer carryTss = 0;
        Double sightRange = (double) 0;
        Boolean isShackle = true;
        Boolean isOptics = true;
        List<Firearm> firearmList = new ArrayList<>();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("src/firearm.xml"));
            NodeList firearms = document.getElementsByTagName("firearm");

            for (int temp = 0; temp < firearms.getLength(); temp++) {
                Node nNode = firearms.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String model = String.format(eElement.getAttribute("model"));
                    String origin = eElement.getElementsByTagName("origin").item(0).getTextContent();
                    for (int i = 0; i < firearms.getLength(); i++) {

                        carryTss = Integer.parseInt(eElement.getElementsByTagName("carry").item(0).getTextContent());
                        sightRange = Double.parseDouble(eElement.getElementsByTagName("sightRange").item(0).getTextContent());
                        isShackle = Boolean.parseBoolean(eElement.getElementsByTagName("shackle").item(0).getTextContent());
                        isOptics = Boolean.parseBoolean(eElement.getElementsByTagName("optics").item(0).getTextContent());
                    }
                    int handy = Integer.parseInt(eElement.getElementsByTagName("handy").item(0).getTextContent());
                    String materials = eElement.getElementsByTagName("materials").item(0).getTextContent();
                    FirearmTTS firearmTTS = new FirearmTTS(carryTss, sightRange, isShackle, isOptics);
                    firearmList.add(new Firearm(model, origin, firearmTTS, handy, materials));
                }
            }
            firearmList.sort(Comparator.comparing(Firearm::getModel));
            for (Firearm periodical : firearmList) {
                LOG.info(periodical);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return firearmList;
    }
}
