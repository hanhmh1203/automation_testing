import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseActivity implements AppActivity{
     AndroidDriver<MobileElement> mDriver;
     WebDriverWait mWait;
     boolean isRunning =false;

    public void init(AndroidDriver<MobileElement> driver, WebDriverWait wait) {
        mDriver = driver;
        mWait = wait;
    }
    public void sleep(long timeMilisecond){

        try {
            Thread.sleep(timeMilisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
