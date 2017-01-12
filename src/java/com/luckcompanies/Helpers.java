/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luckcompanies;


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Value;
/**
 *
 * @author johnkirksey
 */
public class Helpers {
            
    
    public String GetConnectionString()
    {
        Properties prop = new Properties();
        InputStream input = null;
        String cn = "";
        try
        {
           input = Helpers.class.getClassLoader().getResourceAsStream("sql.properties");
           prop.load(input);
           cn = prop.getProperty("conn");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return cn;
    }
}
