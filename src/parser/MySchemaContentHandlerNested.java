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


public class MySchemaContentHandlerNested implements ContentHandler {
    boolean inBstNr = false;
    boolean inKNr = false;
    boolean inStatus = false;
    boolean inBestDat = false;
    boolean inLiefDat = false;
    boolean inRechDat = false;
    boolean inArtList = false;
    boolean inPosNr = false;
    boolean inArtListBstNr = false;
    boolean inArtNr = false;
    boolean inMenge = false;
    
    String sInBstNr;
    String sInKNr;
    String sInStatus;
    String sInBestDat;
    String sInLiefDat;
    String sInRechDat;
    String sInArtList;
    String sInPosNr;
    String sInArtListBstNr;
    String sInArtNr;
    String sInMenge;
    
    
    private final TypeInfoProvider provider;

    
    public MySchemaContentHandlerNested(TypeInfoProvider typeInfoProvider) {
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
        System.out.println(qName);
        if(qName.equals("")){
            
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("End of element" + qName);
        
        if ("ARTIKEL".equals(qName)) { 
        }

    }

        @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        
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