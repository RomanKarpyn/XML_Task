package com.epam.lab.parsing;


import com.epam.lab.model.Firearm;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SAXParser extends DefaultHandler implements Parser {

    private static final Logger LOG = Logger.getLogger(SAXParser.class);

    @Override
    public  List<Firearm> parseDoc() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        List<Firearm> firearmList = null;
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            CustomHandler customHandler = new CustomHandler();
            saxParser.parse(new File("src/firearm.xml"), customHandler);
            firearmList = customHandler.getFirearmsList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        firearmList.sort(Comparator.comparing(Firearm::getModel));
        for (Firearm firearm : firearmList) {
            LOG.info(firearm);
        }
        return firearmList;
    }
}
