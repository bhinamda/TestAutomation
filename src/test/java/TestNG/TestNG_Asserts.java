package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG_Asserts
{
    //Soft Assert
    @Test
    public void testSoft()
    {
        SoftAssert sa=new SoftAssert();
        System.out.println("Test 1 started");
        sa.assertEquals(12,13,"Count mismatch");
        System.out.println("Test 1 completes");
        sa.assertAll();
    }

    //Hard Assert
    @Test
    public void testHard()
    {

        System.out.println("Test 2 started");
        Assert.assertEquals(12,13);
        System.out.println("Test 2 completes");
    }
}