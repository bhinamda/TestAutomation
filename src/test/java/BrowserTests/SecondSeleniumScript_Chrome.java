package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class SecondSeleniumScript_Chrome {
    public static WebDriver driver;


    void launchBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
       //driver.manage().window().maximize();
    }

    //Scrolling the page by pixels, visibility of element, height
    public void scrollTest() throws InterruptedException{

       driver.navigate().to("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
       Thread.sleep(2000);
       //Scroll by pixels
       // ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,4000");

        //scroll by the visibility of element
        WebElement ele=driver.findElement(By.xpath("//input[@type='submit']"));
        Thread.sleep(2000);
       // ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()",ele);

        //scroll at end of the page
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void main(String a[]) throws InterruptedException {
        SecondSeleniumScript_Chrome s=new SecondSeleniumScript_Chrome();
        s.launchBrowser();
        s.scrollTest();
    }
}
