package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Arrays;

public class AlertHandleDemo_Windowsbased {

    public static WebDriver driver;

    public void launch()throws InterruptedException, IOException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();

        //Method 1
        // driver.get("http://username:password@testurl.com");
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        //Method 2
 /*       driver.get("http://the-internet.herokuapp.com/basic_auth");
        Runtime.getRuntime().exec("C:\\Users\\BHAKTI\\Selenium Automation Projects\\FirstSeleniumWebdriverScript\\src\\test\\resources\\Windows Auth scripts\\WindowsAuthentication.exe");
        Thread.sleep(2000);
  */  }

    public static void main(String[] args) throws IOException, InterruptedException{
        AlertHandleDemo_Windowsbased awd=new AlertHandleDemo_Windowsbased();
        awd.launch();
    }
}
