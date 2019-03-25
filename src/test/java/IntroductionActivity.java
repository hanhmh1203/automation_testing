import io.appium.java_client.MobileElement;

public class IntroductionActivity extends  BaseActivity {
    public void run() {
        isRunning = true;
        sleep(2000);
        MobileElement btnSkip = mDriver.findElementById("introduction_screen_skip");
        if(btnSkip!=null){
            btnSkip.click();
        }
        sleep(1000);
        isRunning = false;
    }
}
