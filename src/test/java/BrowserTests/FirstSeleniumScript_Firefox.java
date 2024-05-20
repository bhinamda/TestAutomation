package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirstSeleniumScript_Firefox {

    public static WebDriver driver;
    public void launchBrowser() throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
     //   System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/test/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--start-maximized");
      //  options.addArguments("-headless");
        //For Firefox below command is used instead of options.addArguments("--incognito") which is used for chrome
        options.addPreference("browser.privatebrowsing.autostart", true);
        driver=new FirefoxDriver(options);
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
        WebElement clickLogout=driver.findElement(By.xpath("/html/body/div/div[1]//*[text()='Logout']"));
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

    FirstSeleniumScript_Firefox f=new FirstSeleniumScript_Firefox();
    f.launchBrowser();
    f.login();
    f.title();
    f.logout();
    f.closeBrowser();
    }
}
