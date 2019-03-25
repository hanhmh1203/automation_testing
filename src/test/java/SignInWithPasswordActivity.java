import io.appium.java_client.MobileElement;

public class SignInWithPasswordActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        inputPassword();
    }

    public void inputPassword() {
        sleep(200);
        if(mDriver.currentActivity().contains(ActivityFactory.SIGN_IN_WITH_PASSWORD)){
            MobileElement mobileElement = mDriver.findElementById("inputPassword");
            if (mobileElement != null) {
                mobileElement.sendKeys("123456");
                mDriver.findElementById("progressSendButton").click();
            }
            sleep(4000);
        }

        isRunning = false;
    }

}
