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
public class MyContentHandlerArtikel implements ContentHandler{
    //Fields to check in which 'tag' we are right now.
    //will be set to true on startElement and to false on endElement
    boolean artNr = false;
    boolean artBez = false;
    boolean artPreis = false;
    boolean artKuehl = false;
    boolean artMge = false;
    boolean artAnzBo = false;
    boolean artEdat = false;
    
    String sArtNr;
    String sArtBez;
    String sArtPreis;
    String sArtKuehl;
    String sArtMge;
    String sArtAnzBo;
    String sArtEdat;
            
    
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
        if ("ARTNR".equals(qName)) {
            artNr = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("ARTBEZ".equals(qName)) {
            artBez = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("PREIS".equals(qName)) {
            artPreis = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("KUEHL".equals(qName)) {
            artKuehl = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("MGE".equals(qName)) {
            artMge = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("ANZBO".equals(qName)) {
            artAnzBo = true;
            //System.out.println(attributes.getValue("DT"));
        }
        if ("EDAT".equals(qName)) {
            artEdat = true;
            //System.out.println(attributes.getValue("DT"));
}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        artNr = false;
        artBez = false;
        artPreis = false;
        artKuehl = false;
        artMge = false;
        artAnzBo = false;
        artEdat = false;
        if ("ARTIKEL".equals(qName)) { //we have reached closing tag of 'ARTIKEL' So we can add this article to DB
            database.DatabaseConnection database = new database.DatabaseConnection();
            try {
                database.insertArtikelPraktikum1(sArtNr, sArtBez, sArtPreis, sArtKuehl, sArtMge, sArtAnzBo, sArtEdat);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(MyContentHandlerArtikel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if (artNr) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Nummer: " + val);
            sArtNr = val;
        }
        if (artBez) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Bez: " + val);
            sArtBez = val;
        }
        if (artPreis) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Preis: " + val);
            sArtPreis = val;
        }
        if (artKuehl) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Kuehl: " + val);
            sArtKuehl = val;
        }
        if (artMge) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Mengeneinheit: " + val);
            sArtMge = val;
        }
        if (artAnzBo) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Anzahl pro Box: " + val);
            sArtAnzBo = val;
        }
        if (artEdat) {
            String val = new String(chars, start, length);
            System.out.println("Artikel Edat: " + val);
            sArtEdat = val;
}
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
