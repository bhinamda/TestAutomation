package BrowserTests;

import Utilityclasses.Screenshot_Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Screenshots
{
 public static WebDriver driver;

    public void launch() throws InterruptedException, IOException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        Thread.sleep(2000);
        Screenshot_Utility.captureScreenshot(driver,"Browser started");

    }

    public void captureScreenshots() throws IOException
    {
      driver.findElement(By.id("email")).sendKeys("testemail@gmail.com");
        Screenshot_Utility.captureScreenshot(driver,"Type Username");

        //capturing sc of an element
        WebElement btn=driver.findElement(By.name("login"));
        File src=btn.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("./src/test/resources/Screenshots/element.png"));
        driver.quit();
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {

        Screenshots s=new Screenshots();
        s.launch();
        s.captureScreenshots();
    }
}
