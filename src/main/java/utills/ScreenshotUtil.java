package utills;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private final String timestamp;
    private final String saveDir;

    public ScreenshotUtil() {
        this.timestamp = generateTimestamp();
        this.saveDir = generateSaveDir();
    }

    /// Generates a timestamp in "yyyy.MM.dd-HH.mm.ss" format.
    private String generateTimestamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        return formatter.format(new Date());
    }

    /// Creates a "screenshots" directory if it doesn't exist.
    private String generateSaveDir(){
        String saveDir = System.getProperty("user.dir") + "\\screenshots\\";

        File saveDirectory = new File(saveDir);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }
        return saveDir;
    }

    /// Captures a screenshot and saves it with the given file name.
    public void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);

        String destinationPath = saveDir + fileName + " " + timestamp + ".png";
        File destinationFile = new File(destinationPath);

        FileHandler.copy(sourcefile, destinationFile);
    }

    /// Getter for the timestamp.
    public String getTimestamp() {
        return timestamp;
    }
}
