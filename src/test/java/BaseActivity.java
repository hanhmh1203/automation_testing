import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseActivity implements AppActivity{
     AndroidDriver<MobileElement> mDriver;
     boolean isRunning =false;
    public static Boolean isNali = true;

    public void init(AndroidDriver<MobileElement> driver) {
        mDriver = driver;
    }
    public void sleep(long timeMilisecond){

        try {
            Thread.sleep(timeMilisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
