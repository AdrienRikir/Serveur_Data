/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.beans.*;
import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author Adrien
 */
public class BeanBDAccess implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    private String sampleProperty;
    
    private Connection con;
    
    private PropertyChangeSupport propertySupport;
    
    public Connection getConnection()
    {
        return con;
    }
    
    public BeanBDAccess(String url) {
        propertySupport = new PropertyChangeSupport(this);
        System.out.println("Essai de connexion à MySQL\n");
        try{
            Class leDriver = Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver JDBC chargé\n");           
        }
        catch(ClassNotFoundException e)
        {System.out.println("Driver adéquat non trouvable : " + e.getMessage());}
        
        try{
            con = DriverManager.getConnection(url,"root","root");
            System.out.println("Connexion à la BDD réalisée.\n");
        }
        catch(SQLException e)
        {System.out.println("Erreur SQL : " + e.getMessage());}
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
