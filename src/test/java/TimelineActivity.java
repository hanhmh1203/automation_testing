import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import readfile.LatLonEntity;
import readfile.ReadFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimelineActivity extends BaseActivity {
    public void run() {

        if (isNali) {
            runWithNali();
            System.out.println("TimelineActivity " + mDriver.currentActivity());
            closeImageTimeline();
//            logoutApp();
        } else {
            sleep(2000);
            System.out.println("TimelineActivity " + mDriver.currentActivity());
            sleep(2000);
            closeImageTimeline();
            System.out.println("TimelineActivity " + mDriver.currentActivity());
            sleep(500);
            clickOnStatusNew();
            System.out.println("TimelineActivity " + mDriver.currentActivity());
            sleep(2000);

        }
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
                System.out.println("status CONFIRMED click ");
                break;
            }
            if (mobileElement.getText().contains("ON-HOLD")) {
                mobileElement.click();
                System.out.println("status ON-HOLD click ");
                break;
            }
            if (mobileElement.getText().contains("NEW")) {
                mobileElement.click();
                System.out.println("status NEW click ");
                break;
            }
            if (mobileElement.getText().contains("STARTED")) {
                mobileElement.click();
                System.out.println("status STARTED click ");
                break;
            }

        }
    }

    private void byPassTutorialScreen() {
        new TouchAction(mDriver).tap(PointOption.point(200, 200)).perform();
        sleep(500);
    }

    List<LatLonEntity> locations;

    private void runWithNali() {
        locations = ReadFile.getListLocation();
        System.out.println("AssignmentDetailActivity post location start");

        for (int i = 0; i < locations.size(); i++) {
            LatLonEntity entity = locations.get(i);
            System.out.println("AssignmentDetailActivity post location -" + entity.toString());
            mDriver.setLocation(new Location(Double.parseDouble(entity.getLat()), Double.parseDouble(entity.getLon()), 1));
            System.out.println("AssignmentDetailActivity get location -" + mDriver.location().getLatitude() + " - " + mDriver.location().getLongitude());
            showTimeCurrent();
            try {
                synchronized (mDriver) {
                    if (entity.getTimeDelay() > 0) {
                        mDriver.wait(entity.getTimeDelay() * 1000);
                        System.out.println("AssignmentDetailActivity get location -" + mDriver.location().getLatitude() + " - " + mDriver.location().getLongitude());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("AssignmentDetailActivity post location done");
//        try {
//            synchronized (mDriver) {
//                mDriver.wait(20 * 1000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("AssignmentDetailActivity post location stop");

    }

    private void showTimeCurrent() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); //2016/11/16 12:08:43
    }


    public void openProfile() throws InterruptedException {
        int loopCount = 5;
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/fragment_menu_profile_btn"));
            if (list != null && list.size() > 0) {
                list.get(0).click();
                break;
            }
            sleep(200);
        }
    }

    public void logOut() throws InterruptedException {
        int loopCount = 5;
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/suSignOut"));
            if (list != null && list.size() > 0) {
                list.get(0).click();
                break;
            }
            sleep(200);
        }

        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_positive"));
            if (list != null && list.size() > 0) {
                list.get(0).click();
                break;
            }
            sleep(200);
        }
    }

    public void logoutApp() {
        try {
            openProfile();
            logOut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
