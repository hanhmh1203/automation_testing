import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class SigninActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        inputUsername();
    }

    public void inputUsername() {
        sleep(500);
        if(mDriver.currentActivity().contains(ActivityFactory.SIGN_IN_ACTIVITY)){
            MobileElement mobileElement = mDriver.findElementById("asuPhoneNumber");
            if(mobileElement!=null){
                mobileElement.sendKeys("+84906653665");
                mDriver.findElementById("progressPasswordButton").click();
            }
            sleep(2000);
        }

        isRunning = false;
    }
}
