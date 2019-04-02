import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimelineActivity extends BaseActivity {
    public void run() {
        System.out.println("TimelineActivity " + mDriver.currentActivity());
        sleep(2000);
        closeImageTimeline();
        System.out.println("TimelineActivity " + mDriver.currentActivity());
        sleep(500);
        clickOnStatusNew();
        System.out.println("TimelineActivity " + mDriver.currentActivity());
        sleep(2000);
    }

    public void closeImageTimeline() {


        if (mDriver.currentActivity().contains(ActivityFactory.TIMELINE_ACTIVITY)) {
            byPassTutorialScreen();
        }
    }

    private void clickOnStatusNew() {
        List<MobileElement> feeds = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/feed_assignment_status"));
        for (MobileElement mobileElement : feeds) {
            System.out.println("status assign " + mobileElement.getText());
            if (mobileElement.getText().contains("CONFIRMED")) {
                mobileElement.click();
                System.out.println("status CONFIRMED click " );
                break;
            }
            if (mobileElement.getText().contains("ON-HOLD")) {
                mobileElement.click();
                System.out.println("status ON-HOLD click " );
                break;
            }
            if (mobileElement.getText().contains("NEW")) {
                mobileElement.click();
                System.out.println("status NEW click " );
                break;
            }
            if (mobileElement.getText().contains("STARTED")) {
                mobileElement.click();
                System.out.println("status STARTED click " );
                break;
            }

        }
    }

    private void byPassTutorialScreen() {
        new TouchAction(mDriver).tap(PointOption.point(200, 200)).perform();
        sleep(500);
    }
}
