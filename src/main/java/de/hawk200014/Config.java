package de.hawk200014;

import java.io.*;
import java.util.Properties;

public class Config {
    private File configFile;

    public Config(File configFile){
        this.configFile = configFile;
    }

    public String getProperty(String property){
        try {
            FileReader reader = new FileReader(this.configFile);
            Properties props = new Properties();
            props.load(reader);

            String host = props.getProperty(property);

            reader.close();

            if(host == null) return "";
            return host;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void saveProperty(String property, String value, String comment){
        try (Writer inputStream = new FileWriter(this.configFile)) {
            FileInputStream propsInput = new FileInputStream(this.configFile);

            Properties prop = new Properties();
            prop.load(propsInput);
            // Setting the properties.
            prop.setProperty(property, value);

            // Storing the properties in the file with a heading comment.
            prop.store(inputStream, comment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
