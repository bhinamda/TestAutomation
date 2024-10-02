package TestNG.TestNG_Listeners;

import com.google.gson.JsonParseException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener
{
    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
        System.out.println("Test case started and result is:"+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case success and result is:"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case failed and result is:"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case skipped and result is:"+result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test started and result is:"+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished and result is:"+context.getName());
    }
}
