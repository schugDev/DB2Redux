/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author zeroth
 */
public class BestellA {
    private int  posNr;
    private int artNr;
    private String artBez;
    private double artPreis;
    private String artKuehl;
    private String artMge;
    private String artAnzBo;
    private Date artEdat;

    public BestellA(int posNr, int artNr, String artBez, double artPreis, String artKuehl, String artMge, String artAnzBo, Date artEdat) {
        this.posNr = posNr;
        this.artNr = artNr;
        this.artBez = artBez;
        this.artPreis = artPreis;
        this.artKuehl = artKuehl;
        this.artMge = artMge;
        this.artAnzBo = artAnzBo;
        this.artEdat = artEdat;
    }

    public int getPosNr() {
        return posNr;
    }

    public void setPosNr(int posNr) {
        this.posNr = posNr;
    }

    public int getArtNr() {
        return artNr;
    }

    public void setArtNr(int artNr) {
        this.artNr = artNr;
    }

    public String getArtBez() {
        return artBez;
    }

    public void setArtBez(String artBez) {
        this.artBez = artBez;
    }

    public double getArtPreis() {
        return artPreis;
    }

    public void setArtPreis(double artPreis) {
        this.artPreis = artPreis;
    }

    public String getArtKuehl() {
        return artKuehl;
    }

    public void setArtKuehl(String artKuehl) {
        this.artKuehl = artKuehl;
    }

    public String getArtMge() {
        return artMge;
    }

    public void setArtMge(String artMge) {
        this.artMge = artMge;
    }

    public String getArtAnzBo() {
        return artAnzBo;
    }

    public void setArtAnzBo(String artAnzBo) {
        this.artAnzBo = artAnzBo;
    }

    public Date getArtEdat() {
        return artEdat;
    }

    public void setArtEdat(Date artEdat) {
        this.artEdat = artEdat;
    }
    
    
    
}
