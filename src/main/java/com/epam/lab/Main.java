package com.epam.lab;


import com.epam.lab.model.Firearm;
import com.epam.lab.model.FirearmTTS;
import com.epam.lab.parsing.Parser;
import com.epam.lab.parsing.ParserSelector;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Scanner sc = new Scanner(System.in);
    private static final String XSD_PATH = "src/firearm.xsd";
    private static final String XML_PATH = "src/firearm.xml";
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Set<FirearmTTS> types = new HashSet<>();

        ParserSelector parserSelector = new ParserSelector();
        XMLWork xmlWork = new XMLWork();
        LOG.info("choose parser you want parse xml enter 1 -DOMParser,2-SAXParser,3-StAX Parser");
        boolean rightChoice = true;
        while (rightChoice) {
            int choice = sc.nextInt();
            Parser parser = parserSelector.getParser(choice);
            if (parser != null) {
                LOG.info("Collection of periodicals sorted by pages");
                List<Firearm> firearms = parser.parseDoc();
                xmlWork.validateXMLSchema(XSD_PATH, XML_PATH);
                xmlWork.generateHTML();
                xmlWork.generateXMLWithChangedRoot();
                rightChoice = false;

                for (Firearm firearm : firearms) {

                    types.add(firearm.getTts());
                }
            }
        }
    }
}
