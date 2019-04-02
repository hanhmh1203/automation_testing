import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

public class SigninActivity extends BaseActivity {
    public void run() {
        isRunning = true;
        inputUsername();
    }

    public void inputUsername() {
//        sleep(2000);
        if(mDriver.currentActivity().contains(ActivityFactory.SIGN_IN_ACTIVITY)){
//            AndroidElement androidElement =mDriver.findElement(MobileBy.AndroidUIAutomator("asuPhoneNumber")).c;
            for(int i=0;i<10;i++){
                List<MobileElement> list= mDriver.findElementsById("asuPhoneNumber");
                if(list!=null &&list.size()>0){
                    list.get(0).sendKeys("+84906653665");
                    System.out.println("sendkeys 84906653665");
                    mDriver.findElementById("progressPasswordButton").click();
                    System.out.println("click type password");
                    break;
                }
                sleep(300);
            }

            sleep(3000);
        }

        isRunning = false;
    }
}
