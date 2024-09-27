package TestNG;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class sampleTestNG_class
{
    public static WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://seleniumpractise.blogspot.com/");
        Thread.sleep(2000);
    }
    @Test
    public void titleTest()
    {
        String Title=driver.getTitle();
        System.out.println("Title os the page is:"+Title);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}
