package de.hsos.swa.ssa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class DBProperties {
    private static final String PROPERTY_LOCATION = "db.properties";

    private static final String DBNAME_PROPERTY_KEY = "dbname";
    private static final String DBNAME_PROPERTY_DEFAULT = "simpleshoppingapp";



    private static Properties properties = null;
    
    private static Properties getProperties(){
        if(properties == null){
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties(){
        try(InputStream fileInputStream = new FileInputStream(PROPERTY_LOCATION)){
            properties = new Properties();
            properties.load(fileInputStream);
        }catch(FileNotFoundException ex){
            createProperties();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    private static void createProperties(){
        properties = new Properties();
        setDefaults();
        properties.setProperty(DBNAME_PROPERTY_KEY, DBNAME_PROPERTY_DEFAULT);
        try(OutputStream fileOutputStream = new FileOutputStream(PROPERTY_LOCATION)){
            properties.store(fileOutputStream,"COMMENT");
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    private static void setDefaults(){
        properties.setProperty(DBNAME_PROPERTY_KEY, DBNAME_PROPERTY_DEFAULT);
    }


    public static String getDBName(){
        return getProperties().getProperty(DBNAME_PROPERTY_KEY, DBNAME_PROPERTY_DEFAULT);
    }


    public static String getURL(){
        return String.format("jdbc:derby:%s", getDBName());
    }

}
