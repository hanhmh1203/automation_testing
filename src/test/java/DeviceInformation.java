import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DeviceInformation {
    private static int NEW_COMMAND_TIMEOUT = 60 * 100;
    private static String PLATFORMNAME = "Android";
    public static DesiredCapabilities samsungJ7() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "4200a82fd2dd65e5");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORMNAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, NEW_COMMAND_TIMEOUT);
//        caps.setCapability("uiautomator2ServerLaunchTimeout", "30000");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.gear71.nightly.android");
        caps.setCapability("appActivity", "com.gear71.android.ui.screen.launch.LaunchActivity");
        caps.setCapability("noReset", "true");
        caps.setCapability("dontStopAppOnReset", true);
        return caps;
    }
}
