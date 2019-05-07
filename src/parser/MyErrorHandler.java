package parser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.getMessage() + " on " + exception.getLineNumber() + " " + exception.getColumnNumber());
        
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("Error: " + exception.getMessage() + " on " + exception.getLineNumber() + " " + exception.getColumnNumber());
        System.out.println("Programmabbruch");
        System.exit(2);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("FatalError: " + exception.getMessage() + " on " + exception.getLineNumber() + " " + exception.getColumnNumber());
        System.out.println("Programmabbruch");
        System.exit(3);
    }
}