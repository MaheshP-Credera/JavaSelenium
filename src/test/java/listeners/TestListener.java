package listeners;

import baseTest.BasePageTest;
import com.aventstack.extentreports.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    public static ExtentReports extent =
            ExtentManager.getInstance();

    public static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName());

        test.set(extentTest);

        System.out.println("Extent Test Created");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {

            String path =
                    ScreenshotUtil.captureScreenshot(
                            BasePageTest.driver,
                            result.getMethod().getMethodName());

            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}