/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author zeroth
 */
public class BestellA {
    private int bstNr;
    private int kNr;
    private int status;
    private double rsum;
    private Date bestDat;
    private Date liefDat;
    private Date rechDat;
    public ArrayList<Artikel> artListe = new ArrayList<>();

    public BestellA() {
    }

    public BestellA(int bstNr, int kNr, int status, double rsum, Date bestDat, Date liefDat, Date rechDat) {
        this.bstNr = bstNr;
        this.kNr = kNr;
        this.status = status;
        this.rsum = rsum;
        this.bestDat = bestDat;
        this.liefDat = liefDat;
        this.rechDat = rechDat;
    }

    public int getBstNr() {
        return bstNr;
    }

    public void setBstNr(int bstNr) {
        this.bstNr = bstNr;
    }

    public int getkNr() {
        return kNr;
    }

    public void setkNr(int kNr) {
        this.kNr = kNr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRsum() {
        return rsum;
    }

    public void setRsum(double rsum) {
        this.rsum = rsum;
    }

    public Date getBestDat() {
        return bestDat;
    }

    public void setBestDat(Date bestDat) {
        this.bestDat = bestDat;
    }

    public Date getLiefDat() {
        return liefDat;
    }

    public void setLiefDat(Date liefDat) {
        this.liefDat = liefDat;
    }

    public Date getRechDat() {
        return rechDat;
    }

    public void setRechDat(Date rechDat) {
        this.rechDat = rechDat;
    }

    public ArrayList<Artikel> getArtListe() {
        return artListe;
    }

    public void setArtListe(ArrayList<Artikel> artListe) {
        this.artListe = artListe;
    }
    
    public void printList(){
        for(Artikel a: artListe){
            System.out.println(a.getArtNr() + " " + a.getArtBez() );
        }
    }


    
   
    
}
