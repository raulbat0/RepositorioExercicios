/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.raul.util;

/**
 *
 * @author Raúl
 */
public class ParamDB {
    
    private String urlDB;
    private String userDB;
    private String senhaDB;

    public ParamDB(String urlDB, String userDB, String senhaDB) {
        this.urlDB = urlDB;
        this.userDB = userDB;
        this.senhaDB = senhaDB;
    }

    public ParamDB() {
    }

    public String getUrlDB() {
        return urlDB;
    }

    public void setUrlDB(String urlDB) {
        this.urlDB = urlDB;
    }

    public String getUserDB() {
        return userDB;
    }

    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }

    public String getSenhaDB() {
        return senhaDB;
    }

    public void setSenhaDB(String senhaDB) {
        this.senhaDB = senhaDB;
    }
        
}
