/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import database.DatabaseConnection;
import entity.Artikel;
import entity.Bpos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author zaya
 */
public class CSVWriter {
    
    public void writeCsv() throws SQLException, IOException{
        //Hole alle Artikel aus der Artikeltabelle
        
        //extrahiere darauf ARTNR, ARTBEZ, MGE, PREIS
        
        //hole aus BPOS BESTNR und MENGE fuer jeden Artikel
        
        DatabaseConnection db = new DatabaseConnection();
        File csvFile = new File("ARTIKEL.CSV");
        FileWriter fw = new FileWriter(csvFile);
        
        //System.out.println("ARTNR" + "          " + "ARTBEZ" + "            " + "           MGE" + "           " + "PREIS" + "         " + "BSTNR" + "         " + "MENGE");
        String header = "ARTNR,ARTBEZ,ARTMGE,ARTPREIS,BSTNR,MENGE\n";
        fw.write(header);
        for(Artikel a: db.selectAllArtikel() ){
            
            for(Bpos b: db.getBestNrAndMengeByArtNr(a.getArtNr())){
                //System.out.println(a.getArtNr() + "       " + a.getArtBez() + "   " + a.getArtMge() + "             " + a.getArtPreis() + "             " + b.getBestNr() + "           " + b.getMenge() );
                String line = a.getArtNr() + "," + a.getArtBez().trim() + "," + a.getArtMge() + "," + a.getArtPreis() + "," + b.getBestNr() + "," + b.getMenge() +"\n";
                System.out.println(line);
                fw.write(line);
            }
            
            
            
            
        }
        fw.flush();
            fw.close();
        
        
    }
}
