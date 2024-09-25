package BrowserTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicWebtables {

    public static WebDriver driver;

     public void launch() throws InterruptedException
     {
         WebDriverManager.chromedriver().setup();
         ChromeOptions options=new ChromeOptions();
         options.addArguments("--incognito");
         options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
         driver=new ChromeDriver(options);
         driver.manage().deleteAllCookies();
         driver.manage().window().maximize();
         driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
         driver.get("https://seleniumpractise.blogspot.com/");
         Thread.sleep(2000);
     }

     public void Webtables() throws InterruptedException, IOException {
//table headings
        List <WebElement> table_headings=driver.findElements(By.xpath("//table[@id='customers']//tbody//th"));
         System.out.println("Table headings are as follows with no of headings as:"+table_headings.size());
         Assert.assertEquals(table_headings.size(),5);
         boolean status=false;
        for(WebElement ele:table_headings)
        {
           String headings= ele.getText();
            System.out.println(headings);
            if(headings.contains("Country"))
            {
                status=true;
            }
        }
        Assert.assertTrue(status);

        //capturing table data

         //list rows
        List<WebElement> rows=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
        Assert.assertEquals(rows.size(),7,"table row count mismatched" );

         //check if ola is present in the table

         List <WebElement> table_data=driver.findElements(By.xpath("//table[@id='customers']//td"));
         boolean data_status=false;
        for(WebElement data:table_data)
        {
            String text=data.getText();
            System.out.println(text);
            if(text.contains("Ola"))
            {
                data_status=true;
                break;
            }
        }
        Assert.assertTrue(data_status,"no record found");

        //verify if companies are 6 and java is present or not

         List <WebElement> companyNames=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td[2]"));

         boolean companyStatus=false;

         System.out.println("Companies are as follows : ");
         for(WebElement c:companyNames)
         {
             String companiesText=c.getText();
             System.out.println(companiesText);
             if(companiesText.contains("Java"))
             {
                 companyStatus=true;
                 break;
             }
         }
         Assert.assertTrue(companyStatus,"Could not find company");

         //click the checkbox/element before selenium
         //click the link next to ola

         driver.findElement(By.xpath("//td[text()='Selenium']//preceding-sibling::td//input")).click();
         driver.findElement(By.xpath("//td[text()='Ola']//following-sibling::td[3]")).click();

         driver.quit();
     }
    public static void main(String[] args) throws InterruptedException, IOException {
        DynamicWebtables d=new DynamicWebtables();
        d.launch();
        d.Webtables();
    }
}

