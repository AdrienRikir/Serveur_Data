/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.Serializable;

/**
 *
 * @author Adrien
 */
public class ReponseServeur implements Reponse,Serializable {
    
    private int codeRetour;
    public String reponse;
    
    public ReponseServeur(){}
    
    public ReponseServeur(String s){
        reponse = s;
    }
    
    public ReponseServeur(int c, String s){
        codeRetour = c; reponse = s;
    }           
    
    public String getReponse(){
        return reponse;
    }
    
    public void setReponse(String s){
        reponse = s;
    }
    
    @Override
    public int getCode() {
        return codeRetour;   
    }
}
