package BrowserTests;

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
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        System.out.println("Browser is launched");
        driver.get("https://opensource-demo.orangehrmlive.com");
        Thread.sleep(3000);
    }

    public void login() throws InterruptedException
    {
       WebElement Username= driver.findElement(By.name("username"));
       Username.sendKeys("Admin");
        WebElement Password= driver.findElement(By.name("password"));
        Password.sendKeys("admin123");
        WebElement loginButton=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
        loginButton.click();
        Thread.sleep(2000);
    }

    public void title()
    {
        String title=driver.getTitle();
        System.out.println("Title of the page is :"+title);
    }

    public void logout() throws InterruptedException
    {
        WebElement logout=driver.findElement(By.className("oxd-userdropdown-name"));
        logout.click();
        WebElement clickLogout=driver.findElement(By.xpath("//*[@id=\"app\"]//*[text()='Logout']"));
        clickLogout.click();
        System.out.println("logged out of Chrome");
        Thread.sleep(2000);
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
    f.logout();
    f.closeBrowser();
    }
}
