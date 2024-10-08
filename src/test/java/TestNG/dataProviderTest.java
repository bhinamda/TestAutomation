package TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.util.Arrays;

public class dataProviderTest
{
public static WebDriver driver;

@BeforeMethod
public void launch() throws InterruptedException
{
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--incognito");
    options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
    driver = new ChromeDriver(options);
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    Thread.sleep(2000);
}

    @Test(dataProvider = "logincreds")
    public void loginTest(String username, String password) throws InterruptedException
    {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        Thread.sleep(2000);
        driver.close();
    }

    @DataProvider()
    public static Object[][] logincreds()
    {
        return new Object[][]
                {
                        {"admin", "admin123"},
                        {"admin", "admin123"},
                        {"admin", "admin"}
                };
    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }

}
