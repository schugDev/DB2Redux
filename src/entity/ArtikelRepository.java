/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 *
 * @author zaya
 */
public class ArtikelRepository extends CouchDbRepositorySupport<ArtikelCouch> {
    public ArtikelRepository(CouchDbConnector db){
        super(ArtikelCouch.class, db);
    }
}
