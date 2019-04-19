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

public class MainTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    private void setupDriver(DesiredCapabilities caps) {
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 240);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private void setupSamSung(){
        DesiredCapabilities caps = DeviceInformation.samsungJ7();
        setupDriver(caps);
    }

    @BeforeClass
    public void setup(){
        setupSamSung();
        addEvent();
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
        driver.hideKeyboard();
        List<MobileElement> list = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/asuPhoneNumber"));
        if (list != null && list.size() > 0) {
            list.get(0).sendKeys("0906653665");
            System.out.println("sendkeys 0906653665");
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

        logOut();
    }


    public void openProfile() throws InterruptedException {

        driver.findElementById("fragment_menu_profile_btn").click();
    }

    public void logOut()  {

        List<MobileElement> list = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/root_menu_btn"));
        if (list != null && list.size() > 0) {
            list.get(0).click();
        }
        List<MobileElement> list1 = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/fragment_menu_profile_btn"));
        if (list1 != null && list1.size() > 0) {
            list1.get(0).click();
        }
        List<MobileElement> list2 = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/suSignOut"));
        if (list2 != null && list2.size() > 0) {
            list2.get(0).click();
        }
        List<MobileElement> list3 = driver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_positive"));
        if (list3 != null && list3.size() > 0) {
            list3.get(0).click();
        }
    }

    public void logoutApp() throws InterruptedException {
        openProfile();
        logOut();
    }
}
