
package main;

import database.DatabaseConnection;
import entity.CouchArtikel21;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parser.MySAXParser;
import writer.CSVWriter;


public class Praktikum {


    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String filename;
        String xmlFile;
        String xsdFile;
        int input = Integer.MAX_VALUE;
        
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
        
        
        while(input != 0){
            System.out.println("|------------------------------------------------------------------------------|");
            System.out.println("|  Praktikum 1                                                                 |");
            System.out.println("|  1. ARTIKEL.xml auf Wohlgeformtheit und Validitaet pruefen & INSERT in DB    |");
            System.out.println("|  2. UKUNDE.xml auf Wohlgeformtheit und Validitaet pruefen & UPDATE auf DB    |");
            System.out.println("|  Praktikum 2                                                                 |");
            System.out.println("|  3. Validiere ARTIKEL1.xml gegen XML Schema INSERT in DB                     |");
            System.out.println("|  4. Generiere INSERTS auf Nested Table                                       |");
            System.out.println("|  5. Zeige Inhalt der artListe an (Nested Table)                              |");
            System.out.println("|  Praktikum 3                                                                 |");
            System.out.println("|  6. Generiere CSV fuer Artikel                                               |");
            System.out.println("|  7. CouchDB Funktionen                                                       |");
            System.out.println("|                                                                              |");
            System.out.println("|  0. Beenden                                                                  |");
            System.out.println("|------------------------------------------------------------------------------|");
            try {
                input = Integer.parseInt(in.readLine());
            } catch (IOException ex) {
                Logger.getLogger(Praktikum.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            switch(input){
                case 1:
                    filename = "./files/ARTIKEL.xml/";
                    System.out.println("Validating, Parsing " + filename);
                    MySAXParser.parseXMLFileArtikel(filename);
                    break;
                case 2:
                    filename = "./files/UKUNDE.xml/";
                    System.out.println("Validating, Parsing " + filename);
                    MySAXParser.parseXMLFileKunde(filename);
                case 3: 
                    //no clue why these xml and schema file need to be placed in root directory
                    xmlFile = "ARTIKEL1.xml";
                    xsdFile = "ARTIKEL1.xsd";
                    MySAXParser.parseXMLFileArtikelSchema(xmlFile, xsdFile, true);
                    break;
                case 4:
                    xmlFile = "BESTELLAT.xml";
                    xsdFile = "BESTELLAT.xsd";
                    MySAXParser.parseXMLFileArtikelSchemaNested(xmlFile, xsdFile, true);
                    break;
                case 5:
                    DatabaseConnection db = new DatabaseConnection();
            
                try {
                    db.selectAllFromNestedTable();
                } catch (SQLException ex) {
                    Logger.getLogger(Praktikum.class.getName()).log(Level.SEVERE, null, ex);
               }
                break;
                case 6: 
                    CSVWriter wr = new CSVWriter();
                    wr.writeCsv();
                    break;
                    
                case 7:
                    CouchArtikel21 couch = new CouchArtikel21();
                    couch.connect();
                    couch.showMenu();
                    break;
                    
                

                case 0: 
                default:
            }
        }
        
        
        
        
        
        
        
        
        
    }
    
}
