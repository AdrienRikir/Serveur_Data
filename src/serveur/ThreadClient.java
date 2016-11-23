/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class ThreadClient extends Thread {
    private SourceTaches tachesAExecuter;
    private String nom;
    
    private Runnable tacheEnCours;
    
    public ThreadClient(SourceTaches st, String n){
        tachesAExecuter = st; nom = n;
    }
    
    @Override
    public void run()
    {
        while(!isInterrupted())
        {
            try{
                System.out.println("Thread client avant get");
                tacheEnCours = tachesAExecuter.getTache();
            }
            catch(InterruptedException ex){
                Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Run de tacheEnCours");
            tacheEnCours.run();
        }
    }
}
