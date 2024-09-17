package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;
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


        WebElement childWindow=driver.findElement(By.id("windowhandling1"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",childWindow);
        //Opening multiple windows for multiple window handling
        childWindow.click();
        childWindow.click();
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
    }

    //Program (Library) to handle multiple tabs
    //handling windows and tabs is the same. In case of windows, you have to open multiple windows and in case of tabs, you have to open multiple tabs before getting allWindowHandles
    public void multiple_tabsHandle() throws InterruptedException
    {
        driver.navigate().to("https://www.automationtestinginsider.com/p/java-qa.html");
        Thread.sleep(2000);
        WebElement ele=driver.findElement(By.linkText("Java Questions Part1 - Basic Questions"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",ele);
        String parentWindowHandle=driver.getWindowHandle();
        System.out.println("Parent window handle is:"+parentWindowHandle);
        Thread.sleep(2000);
//opening multiple tabs, same is for windows open multiple windows
        driver.findElement(By.linkText("Java Questions Part1 - Basic Questions")).click();
        driver.findElement(By.linkText("Java Questions Part2 - OOPS Concept, Constructors, Static keyword")).click();
        driver.findElement(By.linkText("Java Questions Part4 - Number Program")).click();

        Set<String> allWindowHandles =driver.getWindowHandles();
        Iterator<String> allWindows=allWindowHandles.iterator();
        while(allWindows.hasNext())
         {
         String child=allWindows.next();
         if(!parentWindowHandle.equals(child)){
             driver.switchTo().window(child);
             System.out.println("child url is:"+driver.getCurrentUrl());
             Thread.sleep(2000);
             driver.close();
         }
         driver.switchTo().window(parentWindowHandle);
         }
        driver.quit();
}
/*
//      To switch windows/tabs using indexes
        Set<String> allWindowHandles =driver.getWindowHandles();
        ArrayList<String> al=new ArrayList<>(allWindowHandles);
        driver.switchTo().window(al.get(0));
*/

    public static void main(String[] args) throws InterruptedException{
        WindowHandleDemo w=new WindowHandleDemo();
        w.launch();
        w.windowHandle();
        w.multiple_tabsHandle();
    }
}
