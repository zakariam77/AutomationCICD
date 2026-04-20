package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;
    static {
        try{
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/saucedemo/resources/global.properties");
            prop = new Properties();
            prop.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
