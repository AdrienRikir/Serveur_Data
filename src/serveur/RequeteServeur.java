/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import protocole.Commande;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Adrien
 */
public class RequeteServeur implements Requete, Serializable {

    private Socket socketClient;
    private ObjectOutputStream oos;
    
    private Commande maCommande;  
            
    public RequeteServeur(Commande chu){
        setCommande(chu);
    }
    
    public RequeteServeur(Commande chu, Socket s){
        setCommande(chu); 
        socketClient = s;
    }
    
    public Commande getCommande(){
        return maCommande;
    }
    
    public void setCommande(Commande s){
        maCommande = s;
    }

    @Override
    public Runnable createRunnable(final Socket s) {
        switch (maCommande.getCommandeID()) {
            case "blabla":
                return new Runnable() {
                    @Override
                    public void run() {
                        //blabla(s);
                    }
                };

            default:return null;
        }
    }     
}
