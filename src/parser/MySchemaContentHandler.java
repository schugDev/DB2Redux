package parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.validation.TypeInfoProvider;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySchemaContentHandler implements ContentHandler {

    
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
    private String[] artikel = new String[7];
    private TypeInfoProvider provider;

    
    public MySchemaContentHandler(TypeInfoProvider typeInfoProvider) {
        this.provider = typeInfoProvider;
    }


    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("Beginning of element");
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
        //System.out.println("End of element" + qName);
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
                database.insertArtikelPraktikum2(sArtNr, sArtBez, sArtPreis, sArtKuehl, sArtMge, sArtAnzBo, sArtEdat);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(MySchemaContentHandler.class.getName()).log(Level.SEVERE, null, ex);
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
    public void processingInstruction(String s, String s1) throws SAXException {
    }

    @Override
    public void skippedEntity(String s) throws SAXException {
    }
}