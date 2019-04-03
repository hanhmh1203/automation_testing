import io.appium.java_client.MobileElement;

import java.util.List;

public class IntroductionActivity extends  BaseActivity {
    public void run() {
        isRunning = true;
//        sleep(2000);
        for(int i=0;i<15;i++){
            List<MobileElement> list = mDriver.findElementsById("introduction_screen_skip");
            if(list!=null &&list.size()>0)
            {
                list.get(0).click();
                break;
            }
            sleep(200);
        }
//        MobileElement btnSkip = mDriver.findElementById("introduction_screen_skip");
//        if(btnSkip!=null){
//            btnSkip.click();
//        }
//        sleep(1000);
        isRunning = false;
        sleep(1000);
    }
}
