package Utilityclasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import static org.apache.commons.io.FileUtils.*;

public class Screenshot_Utility
{
    //parametrising the method, if not it will keep overriding the screenhsot with prev screesnhot so gave scrnsht name in prameters
    public static void captureScreenshot(WebDriver driver,String ScreenshotName) throws IOException
    {
        try
        {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("./src/test/resources/Screenshots/" + ScreenshotName + ".png"));
            System.out.println("Screenshot taken");

        }
        catch(Exception e)
        {
            System.out.println("Exception occured while taking a screenshot"+e.getMessage());
        }
    }
}
