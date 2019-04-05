import io.appium.java_client.MobileElement;

import java.util.List;

public class SignInWithPasswordActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        inputPassword();
    }

    public void inputPassword() {
//        sleep(2000);

//        if (mDriver.currentActivity().contains(ActivityFactory.SIGN_IN_WITH_PASSWORD)) {
            for (int i = 0; i < 10; i++) {
                List<MobileElement> list = mDriver.findElementsById("inputPassword");
                if (list != null && list.size() > 0) {
                    list.get(0).sendKeys("123456");
                    mDriver.findElementById("progressSendButton").click();
                    break;
                }
                sleep(200);
//            }

//
//            MobileElement mobileElement = mDriver.findElementById("inputPassword");
//            if (mobileElement != null) {
//                mobileElement.sendKeys("123456");
//                mDriver.findElementById("progressSendButton").click();
//            }
        }

        isRunning = false;
    }

}
