import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.events.api.general.ElementEventListener;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SetupAndroidDriver {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @Test
    public void test1(){
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new ElementEventListener() {
                    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
                        System.out.println(" beforeClickOn");
                    }

                    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
                        System.out.println(" afterClickOn");
                    }

                    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
                        System.out.println(" beforeChangeValueOf");
                    }

                    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
                        System.out.println(" beforeChangeValueOf");
                    }

                    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
                        System.out.println(" afterChangeValueOf");
                    }

                    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
                        System.out.println(" afterChangeValueOf");
                    }

                    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
                        System.out.println(" beforeGetText");
                    }

                    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
                        System.out.println(" afterGetText");
                    }
                }
        );
    }
    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "SamSung");
        caps.setCapability("udid", "4200a82fd2dd65e5"); //DeviceId from "adb devices" command
//        caps.setCapability("udid", "CB5A25JQ3Z"); //DeviceId from "adb devices" command/sony z3
        caps.setCapability("platformName", "Android");
//        caps.setCapability("platformVersion", "6.0.1");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.gear71.nightly.android");
        caps.setCapability("appActivity", "com.gear71.android.ui.screen.launch.LaunchActivity");
//        caps.setCapability("appActivity","com.gear71.android.ui.activity.SignInActivity");
        caps.setCapability("noReset", "false");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);

    }
}
