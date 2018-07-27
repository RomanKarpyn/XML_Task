package com.epam.lab.parsing;


import org.apache.log4j.Logger;

public class ParserSelector {

  private static final Logger LOG = Logger.getLogger(ParserSelector.class);
  private Parser parser = null;

  public Parser getParser(int choice) {
    boolean rightChoice = true;
    while (rightChoice) {
      switch (choice) {
        case 1:
          parser = new DOMParser();
          rightChoice = false;
          break;
        case 2:
          parser = new SAXParser();
          rightChoice = false;
          break;
        case 3:
          parser = new STAXParser();
          rightChoice = false;
          break;

        default:
          LOG.info("enter 1,2 or 3 to choose parser");
          rightChoice = false;
          break;
      }
    }
    return parser;
  }
}
