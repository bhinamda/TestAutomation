package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class FirstSeleniumScript_Chrome {

    public static WebDriver driver;
    public void launchBrowser() throws InterruptedException
    {
       // WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        System.out.println("Browser is launched");
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);
    }

    public void login() throws InterruptedException
    {
       WebElement Username= driver.findElement(By.name("user-name"));
       Username.sendKeys("standard_user");
        WebElement Password= driver.findElement(By.name("password"));
        Password.sendKeys("secret_sauce");
        WebElement loginButton=driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();
        Thread.sleep(2000);
    }

    public void title()
    {
        String title=driver.getTitle();
        System.out.println("Title of the page is :"+title);
    }

    //Locating the elements

    public void checkFunctionalities() throws InterruptedException{
       String currentURL=driver.getCurrentUrl();
       System.out.println(currentURL);
       if(currentURL.equals("https://www.saucedemo.com/inventory.html"))
       {
           System.out.println("Test passed");
       }
       else
       {
           System.out.println("Test failed");
       }
       //System.out.println(driver.getPageSource());
       driver.navigate().to("https://magento.softwaretestingboard.com/");
       Thread.sleep(2000);
       driver.navigate().back();
       Thread.sleep(2000);
       driver.navigate().refresh();
       Thread.sleep(2000);
       driver.navigate().forward();
       Thread.sleep(2000);
        driver.navigate().back();
    }


    public void logout() throws InterruptedException
    {
        WebElement logout=driver.findElement(By.xpath("//*[text()='Open Menu']"));
        logout.click();
        Thread.sleep(2000);
        WebElement clickLogout=driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));
        clickLogout.click();
        Thread.sleep(2000);
        System.out.println("logged out of Chrome");
    }

    public void closeBrowser()
    {
        driver.quit();
    }

    public static void main(String a[]) throws InterruptedException
    {

    FirstSeleniumScript_Chrome f=new FirstSeleniumScript_Chrome();
    f.launchBrowser();
    f.login();
    f.title();
    f.checkFunctionalities();
    f.logout();
    f.closeBrowser();
    }
}
