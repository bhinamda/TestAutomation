package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class WindowHandleDemo {

    public static WebDriver driver;
    public void launch() throws InterruptedException
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

    public void windowHandle() throws InterruptedException {
        //get total windows and print them
        String parentWindowHandle=driver.getWindowHandle();
        System.out.println("Parent window handle is:"+parentWindowHandle);
        Thread.sleep(2000);


        WebElement  childWindow=driver.findElement(By.id("windowhandling1"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",childWindow);

        childWindow.click();
        Thread.sleep(2000);

        Set<String> allWindowHandles =driver.getWindowHandles();
        System.out.println("Total Windows:" +allWindowHandles.size());
        System.out.println("All window handles are as follows:");
        for(String ele:allWindowHandles)
        {
            System.out.println(ele);
        }
        //To switch to child window and perform actions

        Iterator<String> itr=allWindowHandles.iterator();
        while(itr.hasNext())
        {
            String childWinId=itr.next();
            if(!parentWindowHandle.equalsIgnoreCase(childWinId))
            {
                driver.switchTo().window(childWinId);
                System.out.println("child url is:"+driver.getCurrentUrl());
                System.out.println("child url title is:"+driver.getTitle());
                driver.findElement(By.id("searchInput")).sendKeys("Selenium");
                Thread.sleep(2000);
                driver.close();
            }
        }
driver.switchTo().window(parentWindowHandle);
        driver.quit();
    }




    public static void main(String[] args) throws InterruptedException{
        WindowHandleDemo w=new WindowHandleDemo();
        w.launch();
        w.windowHandle();
    }
}
