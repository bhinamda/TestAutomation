package BrowserTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
       // options.addArguments("--headless");
        options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
        //options.add_argument("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--block-new-web-contents");
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
//        driver.navigate().refresh();
//        Thread.sleep(3000);
//        driver.navigate().back();
//        Thread.sleep(2000);
//        driver.navigate().forward();
//        Thread.sleep(3000);
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

        public void checkBox (String hobby) throws InterruptedException
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
                if(hobbies.contains("hobby"))
                {
                    ele.click();
                    Thread.sleep(2000);
                }
            }
        System.out.println("Navigating back to Swag labs website ");
       driver.navigate().back();
       Thread.sleep(2000);
        }

    public void Dropdowntest() throws InterruptedException {
        WebElement bday = driver.findElement(By.id("Birthday_Day"));
        WebElement bmonth = driver.findElement(By.id("Birthday_Month"));
        WebElement byear = driver.findElement(By.id("Birthday_Year"));

        Select selectday = new Select(bday);
        Select selectmonth = new Select(bmonth);
        Select selectyear = new Select(byear);

        selectday.selectByIndex(22);
        Thread.sleep(2000);
        selectmonth.selectByValue("December");
        selectmonth.selectByValue("January");
        selectmonth.selectByValue("February");
        Thread.sleep(2000);
        selectyear.selectByVisibleText("1995");
        Thread.sleep(2000);

        //Get first selected item
        WebElement firstvalue_month = selectmonth.getFirstSelectedOption();
        System.out.println(firstvalue_month.getText());

        //Get all options from the dropdown list

        List<WebElement> allbirthdayoptions = selectday.getOptions();

        for (int i = 1; i < allbirthdayoptions.size(); i++) {
            WebElement all = allbirthdayoptions.get(i);
            System.out.println(all.getAttribute("value"));
            //OR use the below- Advanced for loop
//                for(WebElement ele:allbirthdayoptions)
//                {
//                    System.out.println(ele.getText());
//                }
        }
    }
// //not running because of ads, code is correct
    public void multiselectDropdowntest() throws InterruptedException
    {
            driver.navigate().to("https://demo.mobiscroll.com/jquery/select/multiple-select#");
            Thread.sleep(2000);
           WebElement multiselectDropdown=driver.findElement(By.id("multiple-select-input"));
        multiselectDropdown.click();
        Thread.sleep(4000);
           Select skills=new Select(multiselectDropdown);
           skills.selectByValue("css");
           Thread.sleep(2000);
           skills.selectByIndex(3);
           Thread.sleep(2000);
           skills.selectByVisibleText("html");
           Thread.sleep(2000);
    }

    public int factorial(int no)
    {
        int factorial=no;
        int num=no;
        for(int i=no;i>1;i--)
        {
            factorial=factorial*(no-1);
            no=no-1;
        }
        System.out.println("factorial of " +num+ " is "+factorial);
        return factorial;
    }

    public void bootstrapDropdowntest() throws InterruptedException
    {
        driver.navigate().to("https://www.automationtestinginsider.com/2019/12/bootstrap-dropdown-example_12.html");
        Thread.sleep(2000);
        WebElement bootstrap=driver.findElement(By.id("bootstrapmenu"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",bootstrap);
        bootstrap.click();
        Thread.sleep(2000);

        List <WebElement> options=driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
        for(WebElement ele:options)
        {
            String value=ele.getText();
            System.out.println(value);

            if(value.contains("CONTACT US"))
            {
                ele.click();
                break;
            }
        }
    }

    public void Keyboardmousetest() throws InterruptedException
    {
        driver.navigate().to("https://www.google.com/");
        Thread.sleep(2000);
        WebElement googlesearch=driver.findElement(By.name("q"));
        Actions a=new Actions(driver);

        Action action= a.keyDown(googlesearch, Keys.SHIFT).sendKeys("selenium")
                .keyUp(googlesearch,Keys.SHIFT)
                        .keyDown(googlesearch,Keys.CONTROL).sendKeys("a")
                        .keyUp(googlesearch,Keys.CONTROL)
                        .keyDown(googlesearch,Keys.CONTROL).sendKeys("x")
                        .keyUp(googlesearch,Keys.CONTROL)
                        .keyDown(googlesearch,Keys.CONTROL).sendKeys("v")
                        .keyUp(googlesearch,Keys.CONTROL)
                .build();

        action.perform();
        System.out.println("Keyboard action performed");

                Thread.sleep(2000);
    }

    ////not running because of ads, code is correct
    public void doubleClickTest() throws InterruptedException
    {
        driver.navigate().to("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
        Thread.sleep(2000);
        WebElement dblclick=driver.findElement(By.id("doubleClickBtn"));
      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView[true]",dblclick);
        Actions a =new Actions(driver);
        a.doubleClick(dblclick).perform();
        Thread.sleep(2000);
        //we are going back for the next operations because element is getting overriden by the ads
        driver.navigate().back();
        Thread.sleep(2000);
        WebElement rightclick=driver.findElement(By.id("rightClickBtn"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView[true]",rightclick);
        a.contextClick(rightclick).perform();

    }

    //not running because of ads, code is correct
    public void dragAndDropDemo() throws InterruptedException {

        driver.navigate().to("https://demoqa.com/droppable");
        Thread.sleep(2000);

        WebElement From=driver.findElement(By.id("draggable"));
        WebElement To=driver.findElement(By.id("droppable"));
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView[true]",From);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView[true]",To);
        Actions a=new Actions(driver);
       // a.dragAndDrop(From,To).perform();
      //  a.dragAndDropBy(From,150,150).perform();
        a.clickAndHold(From).moveToElement(To).release().build().perform();
        Thread.sleep(2000);
        System.out.println("Method ran successfully ");
WebElement droppedmsg=driver.findElement(By.xpath("//div/p[text()='Dropped!']"));
if(droppedmsg.isDisplayed())
{
    System.out.println("Success:"+droppedmsg.getText());
}
else {
    System.out.println("failed");
}
    }

    public void tooltipdemo() throws InterruptedException
    {
        //with title attribute
        driver.navigate().to("https://www.automationtestinginsider.com/p/blog-page.html");
        WebElement tooltip=driver.findElement(By.xpath("//input[@class='gsc-input']"));
       String tooltipText=tooltip.getAttribute("Title");
        System.out.println(tooltipText);
        //without using title attribute
        driver.navigate().to("https://www.w3schools.com/css/css_tooltip.asp");
        Thread.sleep(2000);
        WebElement elt=driver.findElement(By.xpath("//div[@class='w3-quarter'][3]//div[@class='tooltip']"));
        Actions a=new Actions(driver);
        a.moveToElement(elt).perform();
        Thread.sleep(2000);
        WebElement tooltiptext=driver.findElement(By.xpath("//span[contains(@class,'tooltip-bottom')]"));
        System.out.println(tooltiptext.getText());
        Thread.sleep(2000);
    }
//correct code but elt is not getting clicking due to overlapping of ads on that element
    public void multiselect() throws InterruptedException
    {
        driver.navigate().to("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
        Thread.sleep(2000);
        List <WebElement>l=driver.findElements(By.xpath("//select[@name='list_box_name[]']/option"));
            Actions a=new Actions(driver);
            Action act=a.keyDown(Keys.CONTROL).click(l.get(0))
                    .keyDown(Keys.CONTROL).click(l.get(1))
                    .keyDown(Keys.CONTROL).click(l.get(2))
                    .keyUp(Keys.CONTROL).build();
            act.perform();
            Thread.sleep(2000);

    }

    public void mouseHover() throws InterruptedException
    {
        driver.navigate().to("https://demoqa.com/menu");
        Thread.sleep(2000);
        WebElement elt = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions a=new Actions(driver);
        a.moveToElement(elt).perform();
        Thread.sleep(2000);
        WebElement subitem=driver.findElement(By.xpath("(//li/a[contains(text(),'Sub Item')])[2]"));
        subitem.click();
        Thread.sleep(2000);
    }

    public void autoSuggestions() throws InterruptedException
    {
    driver.navigate().to("https://www.google.com/");
    Thread.sleep(2000);
    WebElement searchbox=driver.findElement(By.id("APjFqb"));
    searchbox.sendKeys("Selenium");
    Thread.sleep(2000);
    List<WebElement> autos=driver.findElements(By.id("Alh6id"));
    for(WebElement e:autos)
    {
        String text=e.getText();
        if(text.contains(" selenium webdriver"))
        {
            e.click();
            break;
        }
    }
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
//            f.checkFunctionalities();
//          f.radioButton();
//          f.checkBox("Sketching");
//          f.Dropdowntest();
//          f.multiselectDropdowntest();
//          f.factorial(4);
//          f.bootstrapDropdowntest();
//          f.Keyboardmousetest();
//          f.doubleClickTest();
//          f.dragAndDropDemo();
//          f.tooltipdemo();
//          f.multiselect();
//          f.mouseHover();
//          f.autoSuggestions();
            f.logout();
            f.closeBrowser();
        }
    }

