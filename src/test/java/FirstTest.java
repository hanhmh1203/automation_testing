import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

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


    @Test
    public void startLogin() throws InterruptedException {
        Thread.sleep(2000);
        runWithParttern();
//        allowPermission();
//        Thread.sleep(2000);
//        String activity = driver.currentActivity();
//        if (activity.contains("com.gear71.android.ui.activity.SignInActivity")) {
//
//            driver.findElementById("asuPhoneNumber").sendKeys("+84906653665");
//            driver.findElementById("progressPasswordButton").click();
//
//            inputPassword();
//            closeWelcome();
//            allowPermission();
//        }

    }

    private int a = 0;

    public void runWithParttern() throws InterruptedException {
        a++;
        System.out.println(" start loop a = " + a);
        ActivityFactory factory = new ActivityFactory();
        Thread.sleep(500);
        String activityName = driver.currentActivity();
        if (activityName.contains(ActivityFactory.LAUNCHER_ACTIVITY)) {
            Thread.sleep(2000);
        }

        BaseActivity activity = factory.getActivity(activityName);
        if (activity == null) return;
        if (driver.currentActivity().contains(ActivityFactory.TIMELINE_ACTIVITY)) {

            return;
        }

        System.out.println(" name " + driver.currentActivity());
        activity.init(driver, wait);
        activity.run();
        Thread.sleep(2000);

        while (!activity.isRunning) {
            System.out.println(" out loop a = " + a);
            if (!driver.currentActivity().contains(ActivityFactory.TIMELINE_ACTIVITY)) {
                System.out.println(" last activity " + driver.currentActivity());
                runWithParttern();
            }else{
                break;
            }

        }


    }

    public void allowPermission() throws InterruptedException {
        Thread.sleep(2000);
        String activity = driver.currentActivity();

        if (activity.contains("com.android.packageinstaller.permission.ui.GrantPermissionsActivity")) {
            while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
                driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
                break;
            }
        }

    }

    public void inputPassword() throws InterruptedException {
        Thread.sleep(2000);
        String activity = driver.currentActivity();
        if (activity.contains("com.gear71.android.ui.activity.SignInWithPasswordActivity")) {
            driver.findElementById("inputPassword").sendKeys("123456");
            driver.findElementById("progressSendButton").click();
        }


    }

    public void closeWelcome() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElementById("introduction_screen_skip").click();

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

//    @Test
//    public  void test1(){
//
//    }
}
