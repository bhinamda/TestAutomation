package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;

public class AlertHandleDemo_Webbased {

    public static WebDriver driver;
    public void launch()throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
    }

    public void simplealertHandle()throws InterruptedException{
        WebElement simpleAlert=driver.findElement(By.id("simpleAlert"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",simpleAlert);
        simpleAlert.click();
        Thread.sleep(2000);
        Alert alert=driver.switchTo().alert();
        String alertText=alert.getText();
        System.out.println("Alert text is:"+alertText);
        alert.accept();
        Thread.sleep(3000);
    }

    public void confirmationalertHandle()throws InterruptedException {
        WebElement confirmationAlert = driver.findElement(By.id("confirmationAlert"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", confirmationAlert);
        confirmationAlert.click();
        // Thread.sleep(2000);
//when alert appears and disappears before page is loaded and we are not applying any wait or thread.sleep() then use a custom method to
// handle AlertNotPresent exception as below to verify if alert was displayed / present or not

        if (isconfirmationAlertPresent(driver))
        {
            System.out.println("Confirmation Alert is present");
        }
        else
        {
            System.out.println("Confirmation Alert is not present");
        }
       // driver.close();
    }

    public static boolean isconfirmationAlertPresent(WebDriver driver)
    {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Confirmation Alert text is:"+alertText);
            alert.accept();
            return true;// Alert was present and handled
        }
        catch (NoAlertPresentException e)
        {
            return false; // No alert was present
        }
    }

    public void promptalertHandle()throws InterruptedException
    {
            WebElement promptAlert = driver.findElement(By.id("promptAlert"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", promptAlert);
            promptAlert.click();
           // Thread.sleep(2000);
           if (isPromptAlertPresent(driver))
           {
            System.out.println("Prompt Alert is present");
           } else
           {
            System.out.println("Prompt Alert is not present");
           }
           driver.close();
    }

    public static boolean isPromptAlertPresent(WebDriver driver)
    {
        try {
            Alert alert = driver.switchTo().alert();
            // You can fetch the alert text if needed, or perform actions like accept() or dismiss()
            String alertText = alert.getText();
            System.out.println("Prompt Alert text is:" + alertText);
            alert.sendKeys("Yes");
            return true; // Alert was present and handled
            }
        catch (NoAlertPresentException e)
            {
            return false; // No alert was present
             }
    }

    public static void main(String[] args) throws InterruptedException
    {
        AlertHandleDemo_Webbased ad=new AlertHandleDemo_Webbased();
        ad.launch();
        ad.simplealertHandle();
        ad.confirmationalertHandle();
        ad.promptalertHandle();
    }
}

