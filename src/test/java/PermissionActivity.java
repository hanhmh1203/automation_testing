import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PermissionActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        allowPermission();
    }

    public void allowPermission() {
        sleep(200);
        while (mDriver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
            mDriver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
            break;
        }
        isRunning =false;
    }
}
