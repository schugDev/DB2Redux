package parser;

import database.DatabaseConnection;
import entity.Artikel;
import entity.BestellA;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.validation.TypeInfoProvider;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySchemaContentHandlerNested implements ContentHandler {
    
    BestellA eintragBestellAt;
    Artikel art;
    boolean BESTELLAT = false;
    boolean BSTNR = false;
    boolean KNR = false;
    boolean STATUS = false;
    boolean RSUM = false;
    boolean BESTDAT = false;
    boolean LIEFDAT = false;
    boolean RECHDAT = false;
    boolean ARTLISTE = false;
    boolean BESTELLUNG_TYP = false;
    boolean POSNR = false;
    boolean ARTNR = false;
    boolean BESTELLUNG = false;

    int posnr=0;
    
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
        eintragBestellAt.printList();
        
        //if we reach this point we have all information insert something in our database
        

    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("<"+qName+">");
        if("BESTELLAT".equals(qName)){
            eintragBestellAt  = new BestellA();
        }
        if("BSTNR".equals(qName)){
            BSTNR = true;
        }
        if("KNR".equals(qName)){
            KNR = true;
        }
        if("STATUS".equals(qName)){
            STATUS = true;
        }
        if("RSUM".equals(qName)){
            RSUM = true;
        }
        if("BESTDAT".equals(qName)){
            BESTDAT = true;
        }
        if("LIEFDAT".equals(qName)){
            LIEFDAT = true;
        }
        if("RECHDAT".equals(qName)){
            RECHDAT = true;
        }
        if("ARTLISTE".equals(qName)){
            ARTLISTE = true;
        }
        if("BESTELLUNG_TYP".equals(qName)){
            BESTELLUNG_TYP = true;
        }
        if("BESTELLUNG".equals(qName)){
            eintragBestellAt = new BestellA();
            BESTELLUNG = true;
        }
        if("POSNR".equals(qName)){
            POSNR = true;
        }
        if("ARTNR".equals(qName)){
            ARTNR = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("</"+qName+">");

        if("BSTNR".equals(qName)){
            BSTNR = false;
        }
        if("KNR".equals(qName)){
            KNR = false;
        }
        if("STATUS".equals(qName)){
            STATUS = false;
        }
        if("RSUM".equals(qName)){
            RSUM = false;
        }
        if("BESTDAT".equals(qName)){
            BESTDAT = false;
        }
        if("LIEFDAT".equals(qName)){
            LIEFDAT = false;
        }
        if("RECHDAT".equals(qName)){
            RECHDAT = false;
        }
        if("ARTLISTE".equals(qName)){
            ARTLISTE = false;
        }
        if("BESTELLUNG".equals(qName)){
            DatabaseConnection con = new DatabaseConnection();
            try {
                con.insertIntoNestedTable(eintragBestellAt);
            } catch (SQLException ex) {
                Logger.getLogger(MySchemaContentHandlerNested.class.getName()).log(Level.SEVERE, null, ex);
            }
            BESTELLUNG = false;
        }
        if("BESTELLUNG_TYP".equals(qName)){
            
            BESTELLUNG_TYP = false;
        }
        if("POSNR".equals(qName)){
            POSNR = false;
        }
        if("ARTNR".equals(qName)){
            ARTNR = false;
        }

        
        

    }

        @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        System.out.println(new String(chars, start, length));
        DatabaseConnection con = new DatabaseConnection();
        
        if(BSTNR){
           eintragBestellAt.setBstNr(Integer.parseInt(new String(chars, start, length)));
        }
        
        if(KNR){
            eintragBestellAt.setkNr(Integer.parseInt(new String(chars, start, length)));
        }
        
        if(STATUS){
            eintragBestellAt.setStatus(Integer.parseInt(new String(chars, start, length)));
        }
        
        if(RSUM){
            eintragBestellAt.setRsum(Double.parseDouble(new String(chars, start, length)));
        }
        
        if(BESTDAT){
            String bestDat = new String(chars, start, length);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date date;
            try {
                date = sdf1.parse(bestDat);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                eintragBestellAt.setBestDat(sqlDate);
                System.out.println("tttttt"+sqlDate);
            } catch (ParseException ex) {
                Logger.getLogger(MySchemaContentHandlerNested.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
        }
        
        if(RECHDAT){
            String rechDat = new String(chars, start, length);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date date;
            try {
                date = sdf1.parse(rechDat);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                eintragBestellAt.setRechDat(sqlDate);
                System.out.println("tttttt"+sqlDate);
            } catch (ParseException ex) {
                Logger.getLogger(MySchemaContentHandlerNested.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(LIEFDAT){
            String liefDat = new String(chars, start, length);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date date;
            try {
                date = sdf1.parse(liefDat);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                eintragBestellAt.setLiefDat(sqlDate);
                System.out.println("tttttt"+sqlDate);
            } catch (ParseException ex) {
                Logger.getLogger(MySchemaContentHandlerNested.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        if(BESTELLUNG_TYP){
            
            if(POSNR){
                posnr = Integer.parseInt(new String(chars, start, length));
            }
            if(ARTNR){
                try {
                    art = con.getArtikelByID(Integer.parseInt(new String(chars, start, length)));
                    art.setPosNr(posnr);
                    eintragBestellAt.artListe.add(art);
                } catch (SQLException ex) {
                    Logger.getLogger(MySchemaContentHandlerNested.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
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
