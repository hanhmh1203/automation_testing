import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimelineActivity extends BaseActivity {
    public void run() {
        closeImageTimeline();
    }

    public void closeImageTimeline() {
        System.out.println("TimelineActivity " + mDriver.currentActivity());
        sleep(3000);
        if (mDriver.currentActivity().contains(ActivityFactory.TIMELINE_ACTIVITY)) {
//            mDriver.findElement(MobileBy.id("android.support.v7.widget.AppCompatImageView@561375d")).click();
//            System.out.println("TimelineActivity "+mDriver.findElement(MobileBy.id("tutorial_imv")));
//            mDriver.findElement(MobileBy.id("tutorial_imv")).click();
//            mDriver.findElementById("tutorial_imv").click();
//             mDriver.findElement(MobileBy.id("com.gear71.nightly.android:id/tutorial_imv")).click();
//com.gear71.nightly.android:id/tutorial_imv
//            mDriver.findElement(MobileBy.className("android.support.v7.widget.AppCompatImageView@561375d")).click();
//            mDriver.findElement(MobileBy.className("android.widget.ImageView")).click();
//            mDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView").click();

            List<MobileElement> elements =mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/action_bar_root"));

//            List<MobileElement> elements = mDriver.findElements(MobileBy.xpath("//android.widget.FrameLayout/*"));
            for(int i=0;i<elements.size();i++){
                System.out.println("TimelineActivity "+elements.get(i).getTagName());
            }
//            MobileElement mobileElement = mDriver.findElementById("com.gear71.nightly.android:id/root_menu_btn");
            mDriver.findElement(MobileBy.id("com.gear71.nightly.android:id/root_menu_btn")).click();
//            mobileElement.click();
            // switch to Native context to search inside it
//            List<MobileElement> images = mDriver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView");
//             mDriver.findElementByAccessibilityId("com.gear71.nightly.android:id/tutorial_imv").click();
//            for (MobileElement mobileElement:images){
//                System.out.println(mobileElement.getId());
//            }

//            mDriver.findElementByAndroidUIAutomator()
            //Identify an element using Resource ID (exact match)
//            MobileElement mobileElement = ((AndroidDriver<MobileElement>)mDriver).findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.gear71.nightly.android:id/tutorial_imv\")");
//            System.out.println("Search Box Name - " + mobileElement.getAttribute("name"));
//            mobileElement.click();

            sleep(500);
        }

    }
}
