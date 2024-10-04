package TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

//@Listeners(TestNG.TestNG_Listeners.Listener.class)
public class SwagLabsTest {
    public static WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browsername) throws InterruptedException
    {
        if(browsername.equalsIgnoreCase("Chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
            driver = new ChromeDriver(options);
        }
        else if(browsername.equalsIgnoreCase("Firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options=new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addPreference("browser.privatebrowsing.autostart", true);
            driver=new FirefoxDriver(options);
        }
        else if (browsername.equalsIgnoreCase("Edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test(priority=1,description = "This will open the browser")
    public void launch() throws InterruptedException
    {
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
    }

    @Parameters({"username","password"})
    @Test(priority=2)
    public void loginTest(String username,String password) throws InterruptedException
    {
        driver.findElement(By.name("user-name")).sendKeys(username);
       driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        Thread.sleep(2000);
    }

    @Test(priority=3,dependsOnMethods = "loginTest")
    public void currentUrlTest()
        {

        String Actualurl = driver.getCurrentUrl();
        Assert.assertEquals(Actualurl,"https://www.saucedemo.com/inventory.html");
    }

    @Test(priority=4,dependsOnMethods = {"loginTest"})
    public void titleTest()
    {

        String title = driver.getTitle();
        boolean titleDisplayed=title.isBlank();
        Assert.assertTrue(titleDisplayed);
    }

    @Test(priority=5,alwaysRun = true)
    public void LogoutTest() throws InterruptedException
    {
        WebElement logout = driver.findElement(By.xpath("//*[text()='Open Menu']"));
        logout.click();
        Thread.sleep(1000);
        WebElement clickLogout = driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));
        clickLogout.click();
        Thread.sleep(1000);
        String ActualcurrUrl=driver.getCurrentUrl();
        String expectedcurrUrl="https://www.saucedemo.com/";
        Assert.assertEquals(ActualcurrUrl,expectedcurrUrl);
        System.out.println("logged out of Chrome");

    }

    @AfterClass
    public void CloseBrowser()
    {
        driver.close();
    }
}