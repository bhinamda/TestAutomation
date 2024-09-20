package BrowserTests;
import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Arrays;

public class UploadFiles {

    public static WebDriver driver;

    public void launch()throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
        Thread.sleep(2000);
    }

    public void UploadFiles() throws InterruptedException
    {
        WebElement Choosefile=driver.findElement(By.name("fileupload"));
        Actions a=new Actions(driver);
        a.moveToElement(Choosefile).perform();
        Choosefile.sendKeys("C:\\Users\\BHAKTI\\Selenium Automation Projects\\FirstSeleniumWebdriverScript\\src\\test\\resources\\UploadFiles\\TestUpload1.txt");
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        UploadFiles up=new UploadFiles();
        up.launch();
        up.UploadFiles();
    }
}
