package BrowserTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class FirstSeleniumScript_Edge {

    public static WebDriver driver;
    public void launchBrowser() throws InterruptedException
    {
        System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/src/test/resources/msedgedriver.exe");
        EdgeOptions options=new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        driver=new EdgeDriver(options);
        driver.get("https://demoqa.com/");
        System.out.println("Browser is launched");
        Thread.sleep(2000);
    }

    public void title()
    {
        String title=driver.getTitle();
        System.out.println("Title of the page is :"+title);
    }

    public void openElements() throws InterruptedException
    {
        WebElement button=driver.findElement(By.className("banner-image"));
        button.click();
        Thread.sleep(2000);
    }

    public void closeBrowser()
    {
        driver.quit();
    }
    public static void main(String a[]) throws InterruptedException {

    FirstSeleniumScript_Edge f1=new FirstSeleniumScript_Edge();
    f1.launchBrowser();
    f1.title();
    f1.openElements();
    f1.closeBrowser();
    }
}
