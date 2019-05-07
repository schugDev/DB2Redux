/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.TypeInfoProvider;
import javax.xml.validation.ValidatorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
/**
 *
 * @author zeroth
 */
public class MySAXParser {

    public static void parseXMLFileArtikelSchemaNested(String xmlFile, String xsdFile, boolean validating) {
        try {
            MySchemaContentHandlerNested mySchemaContentHandlerNested;
            MyErrorHandler ehandler = new MyErrorHandler();
            ValidatorHandler vHandler;
            
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(validating);
            factory.newSAXParser();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(new File(xsdFile));
            vHandler = schema.newValidatorHandler();
            TypeInfoProvider typeInfoProvider = vHandler.getTypeInfoProvider();
            mySchemaContentHandlerNested = new MySchemaContentHandlerNested(typeInfoProvider);
            vHandler.setContentHandler(mySchemaContentHandlerNested);
            vHandler.setErrorHandler(ehandler);
            
            XMLReader read1 = XMLReaderFactory.createXMLReader();
            if (validating) {
                // Features & Properties for parsing and validating XML files with XML Schema definition
                read1.setFeature("http://xml.org/sax/features/validation", true);
                read1.setFeature("http://apache.org/xml/features/validation/schema", true);
                read1.setFeature("http://apache.org/xml/features/validation/schema-full-checking",true);
                read1.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",xsdFile);
            } // end of if
            read1.setContentHandler(vHandler);
            // read1.setContentHandler(this.handler);
            // read1.setErrorHandler(ehandler);
            read1.parse(xmlFile);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(MySAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private final String fileName;
    private static SAXParser saxparser1;
    private static XMLReader reader1;

    public MySAXParser(String fileName) {
        this.fileName = fileName;
    }
    
    public static void parseXMLFileArtikel(String filename){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        
        try {
            saxparser1 = factory.newSAXParser();
            reader1 = saxparser1.getXMLReader();

            reader1.setContentHandler(new MyContentHandlerArtikel());
            reader1.setErrorHandler(new MyErrorHandler());
            reader1.parse(new File(filename).toURI().toString());
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            Logger.getLogger(MySAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void parseXMLFileKunde(String filename){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        
        try {
            saxparser1 = factory.newSAXParser();
            reader1 = saxparser1.getXMLReader();

            reader1.setContentHandler(new MyContentHandlerKunde());
            reader1.setErrorHandler(new MyErrorHandler());
            reader1.parse(new File(filename).toURI().toString());
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            Logger.getLogger(MySAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void parseXMLFileArtikelSchema(String xmlFile, String xsdFile, boolean validating) {
        
        try {
            MySchemaContentHandler mySchemaContentHandler;
            MyErrorHandler ehandler = new MyErrorHandler();
            ValidatorHandler vHandler;
            
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(validating);
            factory.newSAXParser();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(new File(xsdFile));
            vHandler = schema.newValidatorHandler();
            TypeInfoProvider typeInfoProvider = vHandler.getTypeInfoProvider();
            mySchemaContentHandler = new MySchemaContentHandler(typeInfoProvider);
            vHandler.setContentHandler(mySchemaContentHandler);
            vHandler.setErrorHandler(ehandler);
            
            XMLReader read1 = XMLReaderFactory.createXMLReader();
            if (validating) {
                // Features & Properties for parsing and validating XML files with XML Schema definition
                read1.setFeature("http://xml.org/sax/features/validation", true);
                read1.setFeature("http://apache.org/xml/features/validation/schema", true);
                read1.setFeature("http://apache.org/xml/features/validation/schema-full-checking",true);
                read1.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",xsdFile);
            } // end of if
            read1.setContentHandler(vHandler);
            // read1.setContentHandler(this.handler);
            // read1.setErrorHandler(ehandler);
            read1.parse(xmlFile);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(MySAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
