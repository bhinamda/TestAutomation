package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Waits {

    public static WebDriver driver;

    void launchBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        //options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    void ImplicitWaitTest() throws InterruptedException
    {
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
        WebElement clickbutton=driver.findElement(By.id("testWait123"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",clickbutton);
        clickbutton.click();
        WebElement ele = driver.findElement(By.xpath("//div[text()='Welcome To Automation Testing Insider']"));
        System.out.println(ele.getText());
        //driver.close();
    }

    void ExplicitWaitTest() throws InterruptedException
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement link= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("BrokenLink")));
        link.click();
        System.out.println("Explicit wait link clicked");
        driver.navigate().back();
        Thread.sleep(2000);
    }

    void FluentWaitTest() throws InterruptedException
    {
        FluentWait<WebDriver> fwait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        WebElement elt=fwait.until(driver->driver.findElement(By.name("list_box_name[]")));
        System.out.println("Fluent wait tested");

    }
    //Test for gmail login using explicit wait
public void test() throws  InterruptedException
{
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options=new FirefoxOptions();
    options.addArguments("--start-maximized");
    driver=new FirefoxDriver(options);
    driver.navigate().to("https://www.google.com/intl/en-US/gmail/about/");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[@data-action='sign in']")).click();
    Thread.sleep(2000);
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
    WebElement Username=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
    Username.sendKeys("rajadnyanirmala@gmail.com");
    driver.findElement(By.xpath("//*[text()='Next']")).click();
    WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
    password.sendKeys("inirmala11");
    driver.findElement(By.xpath("//*[text()='Next']")).click();
    Thread.sleep(2000);
}

    public static void main(String a[]) throws InterruptedException {
        Waits w=new Waits();
        w.launchBrowser();
        w.ImplicitWaitTest();
        w.ExplicitWaitTest();
        w.FluentWaitTest();
        w.test();
    }
}



