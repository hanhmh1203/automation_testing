import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface AppActivity {
    public void init(AndroidDriver<MobileElement> driver);
    public void run();
}
