/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import org.codehaus.jackson.annotate.*;
/**
 *
 * @author zaya
 */
@JsonWriteNullProperties(false)
@JsonIgnoreProperties({"id", "revision"})
public class ArtikelCouch {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String revision;
    
    private int artNr;
    private String artBez;
    private double artPreis;
    private String artMge;
    private int bstnr;
    private int menge;

    public ArtikelCouch() {
    }

    public ArtikelCouch(String id, String revision, int artNr, String artBez,  String artMge, double artPreis, int bstnr, int menge) {
        this.id = id;
        this.revision = revision;
        this.artNr = artNr;
        this.artBez = artBez;
        this.artPreis = artPreis;
        this.artMge = artMge;
        this.bstnr = bstnr;
        this.menge = menge;
    }
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
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

    public String getArtMge() {
        return artMge;
    }

    public void setArtMge(String artMge) {
        this.artMge = artMge;
    }

    public int getBstnr() {
        return bstnr;
    }

    public void setBstnr(int bstnr) {
        this.bstnr = bstnr;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
    
    
    
}
