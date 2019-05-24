/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;
/**
 *
 * @author zaya
 */
public class CouchArtikel21 {
    
    private CouchDbConnector couchConnector;
        
    public void connect(){
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder().url("http://feuerbach.nt.fh-koeln.de:5984").build();
        } catch (MalformedURLException ex) {
            System.out.println("MALFORMED URL");
        }
        
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        
        couchConnector = dbInstance.createConnector("Artikel21", true);
    }
    
    public void showMenu() throws IOException{
        Integer sel = Integer.MAX_VALUE;
        String input = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while(sel != 0){
            System.out.println("1. INSERT aus ARTIKEL.CSV");
            System.out.println("2. SELECT alle Artikel");
            System.out.println("3. SELECT einzelnen Artikel");
            System.out.println("4. UPDATE einzelnen Artikel");
            System.out.println("5. DELETE einzelnen Artikel");
            System.out.println("\n 0. Beenden\n\n");
            sel = Integer.parseInt(in.readLine());
         
            switch(sel){
                case 1:
                    String seperator = ",";
                    List<String> completeFile = Files.readAllLines(Paths.get("ARTIKEL.CSV"));
                    completeFile.remove(0); //removes HeaderLine
                    for(String line: completeFile){
                        System.out.println(line);
                        String[] splitted = line.split(seperator);
                        ArtikelCouch aCouch = new ArtikelCouch();
                        aCouch.setArtNr(Integer.parseInt(splitted[0]));
                        aCouch.setArtBez(splitted[1]);
                        aCouch.setArtMge(splitted[2]);
                        aCouch.setArtPreis(Double.parseDouble(splitted[3]));
                        aCouch.setBstnr(Integer.parseInt(splitted[4]));
                        aCouch.setMenge(Integer.parseInt(splitted[5]));
                        couchConnector.create(aCouch);
                        
                    }
                case 2:
                    ViewQuery vq = new ViewQuery().allDocs().includeDocs(true);
                    List<ArtikelCouch> artListe = couchConnector.queryView(vq, ArtikelCouch.class);
                    
                    for(ArtikelCouch ac: artListe){
                        System.out.println(ac.getArtBez());
                    }
                break;
                case 3:
                    System.out.println("ID des abzufragenden Produktes eingeben (_id)");
                    String id = in.readLine();
                    ArtikelRepository repo = new ArtikelRepository(couchConnector);
                    System.out.println("\n Artikel mit id " + id + " " + repo.get(id).getArtBez() + "\n\n");
                    break;
                case 4:
                    System.out.println("ID des Produktes eingeben auf das UPDATE ausgefuehrt werden soll (_id)");
                    System.out.println("Hier wird nur der Preis geaendert");
                    id = in.readLine();
                    repo = new ArtikelRepository(couchConnector);
                    ArtikelCouch ac = repo.get(id);
                    System.out.println("Bitte neuen Preis eingeben");
                    String preisNeu = in.readLine();
                    ac.setArtPreis(Double.parseDouble(preisNeu));
                    repo.update(ac);
                    break;
                case 5:
                    System.out.println("ID des Produktes eingeben auf das geloescht werden soll (_id)");
                    id = in.readLine();
                    repo = new ArtikelRepository(couchConnector);
                     ac = repo.get(id);
                    repo.remove(ac);
                    break;
                    
                case 0:
            }
        }
        
    }
    
    
    
    
}
