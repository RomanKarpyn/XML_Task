package com.epam.lab;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class XMLWork {

  private static final Logger LOG = Logger.getLogger(XMLWork.class);


  void generateHTML() {
    try {

      TransformerFactory tFactory = TransformerFactory.newInstance();

      Transformer transformer =
          tFactory.newTransformer
              (new StreamSource
                  ("src/firearm.xsl"));

      transformer.transform
          (new StreamSource
                  ("src/firearm.xml"),
              new StreamResult
                  (new FileOutputStream("src/firearm.html")));
    } catch (Exception e) {
      e.printStackTrace();
    }
      System.out.println("firearm.html has been generated");

  }

  void validateXMLSchema(String xsdPath, String xmlPath) {

    try {
      SchemaFactory factory =
          SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = factory.newSchema(new File(xsdPath));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlPath)));
        LOG.info("validation successful");
    } catch (IOException | SAXException e) {
      LOG.info("Exception: " + e.getMessage());
    }
  }

  void generateXMLWithChangedRoot() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = null;
    try {
      documentBuilder = factory.newDocumentBuilder();
      Document doc = documentBuilder.parse("src/firearm.xml");

      Element element = doc.getDocumentElement();

      Element newRootElementName = doc.createElement("newRootElementName");

      NamedNodeMap attrs = element.getAttributes();
      for (int i = 0; i < attrs.getLength(); i++) {
        Attr attr2 = (Attr) doc.importNode(attrs.item(i), true);
        newRootElementName.getAttributes().setNamedItem(attr2);
      }

      while (element.hasChildNodes()) {
        newRootElementName.appendChild(element.getFirstChild());
      }

      element.getParentNode().replaceChild(newRootElementName, element);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("src/NewXml.xml"));
      transformer.transform(source, result);
        LOG.info("NewXml.xml file with new root element has been generated ");
    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
      e.printStackTrace();
    }
  }
}
