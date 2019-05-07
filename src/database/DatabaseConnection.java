/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entity.Artikel;
import entity.BestellA;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeroth
 */
public class DatabaseConnection {
    public Connection connect() throws SQLException{
        String driver;
        driver = "oracle.jdbc.driver.OracleDriver";
        Connection dbConnection = null;
        
        try{
            Class.forName(driver).newInstance();
            
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            System.out.println("Error while loading JDBC driver");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@schelling.nt.fh-koeln.de:1521:xe", "dbprak21", "salihguido21");
        }catch(SQLException ex){
            System.out.println("Error while connecting to database");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbConnection;
    }
    
    public void insertArtikelPraktikum1(String sArtNr, String sArtBez, String sArtPreis, 
        String sArtKuehl, String sArtMge, String sArtAnzBo, String sArtEdat) throws SQLException, ParseException{
        Date date1;
        Connection con = connect();
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO TBLARTIKEL (ARTNR, ARTBEZ, PREIS, KUEHL, MGE, ANZBO, EDAT) VALUES(?,?,?,?,?,?,?)";

        preparedStatement = con.prepareStatement(sql);
        
        preparedStatement.setInt(1, Integer.parseInt(sArtNr));
        preparedStatement.setString(2, sArtBez);
        preparedStatement.setDouble(3, Double.parseDouble(sArtPreis));
        preparedStatement.setString(4, sArtKuehl);
        preparedStatement.setString(5, sArtMge);
        preparedStatement.setString(6, sArtAnzBo);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = sdf1.parse(sArtEdat);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        
        preparedStatement.setDate(7, sqlDate);
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateKundePrak1(String kNr, String kUsp, String kDtUsp, String kUwert) throws SQLException {
        Connection con = connect();
        PreparedStatement preparedStatement;
        String sql = "";
        

        System.out.println("----------------------------------");
        System.out.println("Vor UPDATE");
        selectKundePrak1(Integer.parseInt(kNr));
        sql = "UPDATE TBLKUNDE SET " + kUsp + "='" + kUwert + "' WHERE KNR=" + kNr;
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        
        System.out.println("Nach UPDATE");
        selectKundePrak1(Integer.parseInt(kNr));
        System.out.println("----------------------------------");
    }
    
    public void selectKundePrak1(int id) throws SQLException{
        Connection con = connect();
        PreparedStatement stmt;
        ResultSet resultSet;
        String SQL = "SELECT * FROM TBLKUNDE WHERE KNR=" + id;


        stmt = con.prepareStatement(SQL);
        resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int knr = resultSet.getInt("KNR");
            String kname = resultSet.getString("KNAME");
            int kPlz = resultSet.getInt("PLZ");
            String kOrt = resultSet.getString("ORT");
            String kStr = resultSet.getString("STRASSE");
            float kKlimit = resultSet.getFloat("KKLIMIT");
            System.out.println(knr + " " + kname + " " + kPlz + " " + kOrt + " " + kStr + " " + kKlimit);
        }
        stmt.close();
    }

    public void insertArtikelPraktikum2(String sArtNr, String sArtBez, String sArtPreis, 
        String sArtKuehl, String sArtMge, String sArtAnzBo, String sArtEdat) throws SQLException, ParseException {
        Date date1;
        Connection con = connect();
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO TBLARTIKEL (ARTNR, ARTBEZ, PREIS, KUEHL, MGE, ANZBO, EDAT) VALUES(?,?,?,?,?,?,?)";

        preparedStatement = con.prepareStatement(sql);
        
        preparedStatement.setInt(1, Integer.parseInt(sArtNr));
        preparedStatement.setString(2, sArtBez);
        preparedStatement.setDouble(3, Double.parseDouble(sArtPreis));
        preparedStatement.setString(4, sArtKuehl);
        preparedStatement.setString(5, sArtMge);
        preparedStatement.setString(6, sArtAnzBo);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = sdf1.parse(sArtEdat);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        
        preparedStatement.setDate(7, sqlDate);
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    //Propably the ugliest code I ever wrote.
    public void insertIntoNestedTable(BestellA b) throws SQLException{
        Connection con = connect();
        Statement stmt = con.createStatement();
        
        //Create first part of sql insert
        String sql = "INSERT INTO BESTELLAT VALUES (";
        sql += ""+ b.getBstNr()+ ",";
        sql += ""+ b.getkNr()+ ",";
        sql += ""+ b.getStatus()+ ",";
        sql += ""+ b.getRsum()+ ",";
        sql += "TO_DATE('"+ b.getBestDat()+ "','yyyy.mm.dd '),";
        sql += "TO_DATE('"+ b.getLiefDat()+ "','yyyy.mm.dd '),";
        sql += "TO_DATE('"+ b.getRechDat()+ "','yyyy.mm.dd ')";
        if(b.getArtListe().size()>0){
            sql += ", BESTA(";
        }
        
        
        //extract information for the nested table
        ArrayList<Artikel> alist = b.getArtListe();
        for(Artikel a: alist){
            sql += "bestellung_typ("+a.getPosNr()+",";
            sql += "" + a.getArtNr() + ",";
            sql += "'" + a.getArtBez()+ "',";
            sql += "" + a.getArtPreis()+ ",";
            sql += "'" + a.getArtKuehl()+ "',";
            sql += "'" + a.getArtMge()+ "',";
            sql += "'" + a.getArtAnzBo()+ "',";
            sql += "TO_DATE('" + a.getArtEdat()+ "','yyyy.mm.dd'))";
            if(alist.indexOf(a) != alist.size()-1){
                sql += ",";
            }
            
        }
        
        sql += "))";
        stmt.execute(sql);
        
        
    }
    
    public void selectAllFromNestedTable() throws SQLException{
        /*
        SELECT B.* FROM BESTELLAT A,
	TABLE (A.ARTLISTE) B;
        */
        
        Connection con = connect();
        PreparedStatement stmt;
        ResultSet resultSet;
        String SQL = "SELECT B.* FROM BESTELLAT A, TABLE (A.ARTLISTE) B";


        stmt = con.prepareStatement(SQL);
        resultSet = stmt.executeQuery();
        System.out.println("ARTLISTE");
        System.out.println("----------------");
        String r = new String();
        while (resultSet.next()) {
            r = "";
            r += " "+resultSet.getInt("POSNR");
            r += " "+resultSet.getInt("ARTNR");
            r += " "+resultSet.getString("ARTBEZ");
            r += " "+resultSet.getDouble("PREIS");
            r += " "+resultSet.getString("KUEHL");
            r += " "+resultSet.getString("MGE");
            r += " "+resultSet.getString("ANZBO");
            r += " "+resultSet.getString("EDAT");
            System.out.println("---------------------------------------------------------");
            System.out.println(r);        
        }
        
        
        
    }
    
}
