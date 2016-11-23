/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Adrien
 */
public class PropertyLoader {
       
    public static Properties load(String filename) throws IOException, FileNotFoundException
    {
        Properties properties = new Properties();

        try(FileInputStream input = new FileInputStream(filename)) {
        properties.load(input);
            return properties;
        }
    }
}
