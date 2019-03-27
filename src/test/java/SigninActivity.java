import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class SigninActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        inputUsername();
    }

    public void inputUsername() {
        sleep(2000);
        if(mDriver.currentActivity().contains(ActivityFactory.SIGN_IN_ACTIVITY)){
//            AndroidElement androidElement =mDriver.findElement(MobileBy.AndroidUIAutomator("asuPhoneNumber")).c;

            MobileElement mobileElement = mDriver.findElementById("asuPhoneNumber");
            if(mobileElement!=null){
                mobileElement.sendKeys("+84906653665");
                System.out.println("sendkeys 84906653665");
                mDriver.findElementById("progressPasswordButton").click();
                System.out.println("click type password");
            }
//            sleep(500);
        }

        isRunning = false;
    }
}
