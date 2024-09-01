package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;

public class FirstSeleniumScript_Chrome {

    public static WebDriver driver;
    public void launchBrowser() throws InterruptedException
    {
       WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
       System.out.println("Browser is launched");
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
       Thread.sleep(3000);
    }

    public void login() throws InterruptedException
    {
       WebElement Username= driver.findElement(By.name("user-name"));
        Username.sendKeys("standard");
        Username.clear();
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

    //Locating the elements using browser, navigation and webelement methods
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

        //to check displayed, enabled and selected functions for element
       WebElement addToCart= driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]"));
       System.out.println("Is the button displayed:" +addToCart.isDisplayed());
       System.out.println("Is the button enabled:" +addToCart.isEnabled());
       System.out.println("Is the button selected:" +addToCart.isSelected());
       System.out.println("Button's location:" +addToCart.getLocation());
    }

    //radio button verification
    public void radioButton() throws InterruptedException {
        driver.navigate().to("https://www.automationtestinginsider.com/2019/08/student-registration-form.html");
        System.out.println("User is navigated to automationTestingInsider form page");
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(3000);
        WebElement radioButton = driver.findElement(By.xpath("//input[@value='Female']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", radioButton);

        if (!radioButton.isSelected()) {
            radioButton.click();
            System.out.println("Is the radiobutton selected:" + radioButton.isSelected());
        }
        Thread.sleep(2000);

        //Dynamically handling radiobutton
        List<WebElement> radios = driver.findElements(By.xpath(" //input[@name='Gender' and @type='radio']"));
        for (int i = 0; i < radios.size(); i++) {
            WebElement rads = radios.get(i);
            String radiovalues = rads.getAttribute("value");
            System.out.println(radiovalues);
            if(radiovalues.contains("Male"))
            {
                rads.click();
            }
        }
    }
  /*   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       WebElement radiobtn= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='gender-radio-2']")));
       radiobtn.click();
       System.out.println("Radio button is selected:"+radiobtn.isSelected()); //getting false for isSelected()
*/
        public void checkBox () throws InterruptedException
        {
            WebElement checkbox = driver.findElement(By.xpath("//input[@value='Dancing']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", checkbox);

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Thread.sleep(2000);
            System.out.println("Is the checkbox selected:" + checkbox.isSelected());
            //Select all checkboxes
            List<WebElement> list = driver.findElements(By.xpath("//input[@name='Hobby' and @type='checkbox']"));
            for (int i = 0; i < list.size(); i++) {
                WebElement ele = list.get(i);
                String hobbies = ele.getAttribute("value");
                System.out.println(hobbies);

                //Select a particular checkbox from a list of checkboxes
                if(hobbies.contains("Drawing"))
                {
                    ele.click();
                    Thread.sleep(2000);
                }
            }
        System.out.println("Navigating back to Swag labs website ");
       driver.navigate().back();
       Thread.sleep(2000);
        }


        public void logout () throws InterruptedException
        {
            WebElement logout = driver.findElement(By.xpath("//*[text()='Open Menu']"));
            logout.click();
            Thread.sleep(1000);
            WebElement clickLogout = driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));
            clickLogout.click();
            Thread.sleep(1000);
            System.out.println("logged out of Chrome");

        }

        public void closeBrowser ()
        {
            driver.quit();
        }

        public static void main (String a[]) throws InterruptedException
        {
            FirstSeleniumScript_Chrome f = new FirstSeleniumScript_Chrome();
            f.launchBrowser();
            f.login();
            f.title();
            f.checkFunctionalities();
            f.radioButton();
            f.checkBox();
            f.logout();
            f.closeBrowser();
        }

    }
