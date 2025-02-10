package utills;

public class PathUtil {
    public static String getScreenshotRelativePath(String fileName, String timestamp) {
        return "screenshots\\" + fileName + " " + timestamp + ".png";
    }
}
