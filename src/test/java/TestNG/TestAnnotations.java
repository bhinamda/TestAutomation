package TestNG;
import org.testng.annotations.*;

public class TestAnnotations {

    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("Before suite is executed");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.out.println("after suite is executed");
    }

    @BeforeTest
    public void beforeTest()
    {
        System.out.println("Before test is executed");
    }

    @AfterTest
    public void afterTest()
    {
        System.out.println("after test is executed");
    }

    @BeforeClass
    public void beforeClass()
    {
        System.out.println("before class is executed");
    }

    @AfterClass
    public void afterClass()
    {
        System.out.println("after class is executed");
    }

    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("before method is executed");
    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("after method is executed");
    }

    @Test
    public void testmethod1()
    {
        System.out.println("Test method 1 is executed");
    }

    @Test
    public void testmethod2()
    {
        System.out.println("Test method 2 is executed");
    }
}
