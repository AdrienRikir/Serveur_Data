/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import Tools.PropertyLoader;
import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class ThreadServeur extends Thread {
    private int NBRE_THREADS;
    private int port;
    private SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    
    public ThreadServeur(int p, SourceTaches st){
        port = p; tachesAExecuter = st;;
    }
    
    @Override
    public void run()
    {
        try{
            Properties prop = PropertyLoader.load("prop.properties");
            NBRE_THREADS = Integer.parseInt(prop.getProperty("NBRE_THREADS", "vide"));
        }
        catch (IOException ex) {
            Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            SSocket = new ServerSocket(port);
        }
        catch(IOException ex){
            Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Démarrage du pool de threads
        for(int i=0; i<NBRE_THREADS; i++)
        {
            ThreadClient thr = new ThreadClient(tachesAExecuter,"Thread du pool n°" + String.valueOf(i));
            thr.start();
        }
        
        //Mise en attente du serveur
        Socket CSocket = null;
        
        while(!isInterrupted())
        {
            try{
                System.out.println("********** Serveur en attente **********");
                CSocket = SSocket.accept();                
            }
            catch(IOException ex){
                Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);            
            }
            
            ObjectInputStream ois = null;
            Requete req = null;
            try{
                ois = new ObjectInputStream(CSocket.getInputStream());
                req = (Requete)ois.readObject();
                System.out.println("Requete lue par le serveur, instance de " + req.getClass().getName());                
            }
            catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Runnable travail = req.createRunnable(CSocket);
            if(travail != null)
            {
                tachesAExecuter.recordTache(travail);
                System.out.println("Travail mis dans la file");                
            }
            else
                System.out.println("Pas de mise en file");
        }
    }
}
