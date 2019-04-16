import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.events.api.general.ElementEventListener;
import io.appium.java_client.events.api.general.NavigationEventListener;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import readfile.LatLonEntity;
import readfile.ReadFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeClass
    public void setup() throws MalformedURLException {
        setupSamSung();

    }

    private void setupSamSung() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "4200a82fd2dd65e5");
        caps.setCapability("udid", "4200a82fd2dd65e5"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("automationName", "UiAutomator2");
//        caps.setCapability("uiautomator2ServerLaunchTimeout", "30000");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.gear71.nightly.android");
        caps.setCapability("appActivity", "com.gear71.android.ui.screen.launch.LaunchActivity");
        caps.setCapability("noReset", "true");
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("dontStopAppOnReset", true);
//        caps.setCapability("unlockType", "unlockKey");
//        caps.setCapability("unlockKey", "1111");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600 * 100);
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 240);
    }

    private void setupEmulator() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.gear71.nightly.android");
        caps.setCapability("appActivity", "com.gear71.android.ui.screen.launch.LaunchActivity");
        caps.setCapability("noReset", "false");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test()
    public void startLogin() {
        if (BaseActivity.isNali) {
            setLocation();
        }
        login();
    }


    public void addEvent() {
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new NavigationEventListener() {
            public void beforeNavigateTo(String s, WebDriver webDriver) {
                System.out.println(" beforeNavigateTo" + "- " + webDriver.getTitle());
                System.out.println(" beforeNavigateTo" + "- " + driver.currentActivity());

            }

            public void afterNavigateTo(String s, WebDriver webDriver) {
                System.out.println(" afterNavigateTo" + "- " + webDriver.getTitle());
                System.out.println(" afterNavigateTo" + "- " + driver.currentActivity());
            }

            public void beforeNavigateBack(WebDriver webDriver) {
                System.out.println(" beforeNavigateBack" + "- " + webDriver.getTitle());
                System.out.println(" beforeNavigateBack" + "- " + driver.currentActivity());
            }

            public void afterNavigateBack(WebDriver webDriver) {
                System.out.println(" afterNavigateBack" + "- " + webDriver.getTitle());
                System.out.println(" afterNavigateBack" + "- " + driver.currentActivity());
            }

            public void beforeNavigateForward(WebDriver webDriver) {
                System.out.println(" beforeNavigateForward" + "- " + webDriver.getTitle());
                System.out.println(" beforeNavigateForward" + "- " + driver.currentActivity());
            }

            public void afterNavigateForward(WebDriver webDriver) {
                System.out.println(" afterNavigateForward" + "- " + webDriver.getTitle());
                System.out.println(" afterNavigateForward" + "- " + driver.currentActivity());
            }

            public void beforeNavigateRefresh(WebDriver webDriver) {
                System.out.println(" beforeNavigateRefresh" + "- " + webDriver.getTitle());
                System.out.println(" beforeNavigateRefresh" + "- " + driver.currentActivity());
            }

            public void afterNavigateRefresh(WebDriver webDriver) {
                System.out.println(" afterNavigateRefresh" + "- " + webDriver.getTitle());
                System.out.println(" afterNavigateRefresh" + "- " + driver.currentActivity());
            }
        });

    }


    @Test(dependsOnMethods = "startLogin")
    public void testTimeline() {

        ActivityFactory factory = new ActivityFactory();
        String activityName = driver.currentActivity();
        BaseActivity activity = factory.getActivity(activityName);
        activity.init(driver, wait);
        System.out.println(" name " + driver.currentActivity());
        if (activity instanceof TimelineActivity) {
            activity.run();
        }


    }

    private void setLocation() {
        List<LatLonEntity> locations = ReadFile.getListLocation();
        driver.setLocation(new Location(Double.parseDouble(locations.get(0).getLat()), Double.parseDouble(locations.get(0).getLon()), 1));
//        System.out.println("sign in location post " + locations.get(0).toString());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sign in location post " + driver.location().getLatitude() + "-" + driver.location().getLongitude());
    }

    //    @Test(dependsOnMethods = "testTimeline")
    public void testAssignment() {
        ActivityFactory factory = new ActivityFactory();
        String activityName = driver.currentActivity();
        BaseActivity activity = factory.getActivity(activityName);
        if (activity == null) {
            System.out.println("activity null testAssignment " + driver.currentActivity());
        }
        activity.init(driver, wait);
        System.out.println(" testAssignment " + driver.currentActivity());
        if (activity instanceof AssignmentDetailActivity) {
            activity.run();
        }
    }

    public void login() {
        while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
            driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
            break;
        }
        List<MobileElement> list = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/asuPhoneNumber"));
        if (list != null && list.size() > 0) {
            list.get(0).sendKeys("+84906653665");
            System.out.println("sendkeys 84906653665");
            driver.findElementById("progressPasswordButton").click();
            System.out.println("click type password");
        }

        List<MobileElement> list1 = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/inputPassword"));
        if (list1 != null && list1.size() > 0) {
            list1.get(0).sendKeys("123456");
            driver.findElementById("progressSendButton").click();
        }
        List<MobileElement> list2 = driver.findElementsById("introduction_screen_skip");
        if (list2 != null && list2.size() > 0) {
            list2.get(0).click();
        }
        while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
            driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
            break;
        }
        while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
            driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
            break;
        }
        new TouchAction(driver).tap(PointOption.point(200, 200)).perform();
    }


    public void openProfile() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElementById("fragment_menu_profile_btn").click();
    }

    public void logOut() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElementById("suSignOut").click();
        Thread.sleep(1000);
        driver.findElementById("btn_positive").click();
    }

    public void logoutApp() throws InterruptedException {
        openProfile();
        logOut();
    }
}
