package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class IFrameDemo {

    public static WebDriver driver;

public void launch() throws InterruptedException{
    WebDriverManager.chromedriver().setup();
    ChromeOptions options=new ChromeOptions();
    options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
    options.addArguments("--incognito");
    driver=new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
    Thread.sleep(2000);
}

public void iframeTest() throws InterruptedException
{

    //print no of iframes
    List<WebElement> Iframelist=driver.findElements(By.tagName("iframe"));
    System.out.println("No of Iframes are:"+Iframelist.size());

    //print names or id's of all frames
    System.out.println("Names of all Iframe are:");
    for(WebElement ele:Iframelist)
    {
        String frameNames=ele.getAttribute("name");
        System.out.println(frameNames);

        //Switch to a frame and perform some actions
        if(frameNames.contains("iframe_b")){
            driver.switchTo().frame("iframe_b");
            WebElement searchbox=driver.findElement(By.id("searchInput"));
            searchbox.sendKeys("Hello");
            Thread.sleep(2000);
            driver.switchTo().defaultContent();
        }
    }
   Boolean emailidbox=driver.findElement(By.id("email")).isDisplayed();
    System.out.println("email id box from main window is displayed:"+emailidbox);
}

    public static void main(String[] args) throws InterruptedException {
        IFrameDemo I=new IFrameDemo();
        I.launch();
        I.iframeTest();
    }
}





