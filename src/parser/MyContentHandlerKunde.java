/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author zeroth
 */
public class MyContentHandlerKunde implements ContentHandler{
    //Fields to check in which 'tag' we are right now.
    //will be set to true on startElement and to false on endElement

            
    
    @Override
    public void setDocumentLocator(Locator lctr) {
        System.out.println("In setDocumentLocator");
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document");
    }

    @Override
    public void startPrefixMapping(String string, String string1) throws SAXException {
        System.out.println("startPrefixMapping");
    }

    @Override
    public void endPrefixMapping(String string) throws SAXException {
        System.out.println("endPrefixMapping");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName == "UPKUNDE") {
            
            String kNr = attributes.getValue("KNR");
            String kUsp = attributes.getValue("USP");
            String kDtUsp = attributes.getValue("DTUSP");
            String kUwert = attributes.getValue("UWERT");
               
            database.DatabaseConnection database = new database.DatabaseConnection();
            try {
                database.updateKundePrak1(kNr, kUsp, kDtUsp, kUwert);
            } catch (SQLException ex) {
                Logger.getLogger(MyContentHandlerKunde.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        

    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
       
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {
       
    }

    @Override
    public void processingInstruction(String string, String string1) throws SAXException {
        
    }

    @Override
    public void skippedEntity(String string) throws SAXException {
        
    }
    
}
