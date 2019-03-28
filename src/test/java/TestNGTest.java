import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestNGTest {

    @BeforeGroups
    public void BeforeGroups() {
        System.out.println("@BeforeGroups");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("@BeforeClass");
    }
    @Test
    public void test2() {
        System.out.println("test2");
    }
//    groups = {"My group"}
    @Test(dependsOnMethods = { "test3" ,"test2"})
    public void test1() {
        System.out.println("test1");
    }

    @Test()
    public void test3() {
        System.out.println("Inside testSalutationMessage()");
        System.out.println("test3");
    }
    @AfterClass
    public void AfterClass() {
        System.out.println("@AfterClass");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("@AfterMethod");
    }
    @BeforeMethod
    public void BeforeMethod() throws MalformedURLException{
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);

        System.out.println("@BeforeMethod");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "4200a82fd2dd65e5");
        caps.setCapability("udid", "4200a82fd2dd65e5"); //DeviceId from "adb devices" command
//        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
//        caps.setCapability("udid", "4200a82fd2dd65e5"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("appPackage", "com.gear71.nightly.android");
        caps.setCapability("appActivity", "com.gear71.android.ui.screen.launch.LaunchActivity");
            driver = new AndroidDriver<MobileElement>(url, caps);
            driver.closeApp();
//            wait = new WebDriverWait(driver, 10);

    }
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
}
