package utills;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropFileReaderUtil {

    /// Loads the properties from a given file
    private Properties getData(String fileName){

        /// Constructing the path to the properties file
        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName + ".properties");

        /// Creating a FileInputStream to read the file
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /// Creating a Properties object to load the data from the file
        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /// Returning the loaded properties
        return properties;
    }

    /// Retrieves a specific property value based on the key from the given file
    public String getProperty(String filename, String key){
        return getData(filename).getProperty(key);
    }
}
