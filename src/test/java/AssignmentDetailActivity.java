import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;
import readfile.LatLonEntity;
import readfile.ReadFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class AssignmentDetailActivity extends BaseActivity {
    private int timeDelay = 1000;
    private int timeDelayLocations =1000*30;
    private int timeSleepLoop = 200;
    private int loopCount =3000/timeSleepLoop;
    List<LatLonEntity>locations;
    public void run() {
        System.out.println("AssignmentDetailActivity " + mDriver.currentActivity());
//        mDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


        runWithNali();

    }
    private void runWithNali(){
        locations = ReadFile.getListLocation();
        System.out.println("AssignmentDetailActivity post location start");
//        mDriver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        for(int i=0;i<locations.size();i++)
        {
            mDriver.setLocation(new Location(Double.parseDouble(locations.get(i).getLat()),Double.parseDouble(locations.get(i).getLon()),1));
            try {
                synchronized (mDriver)
                {
                    mDriver.wait(timeDelayLocations);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            sleep(timeDelayLocations);
            System.out.println("AssignmentDetailActivity post location -"+System.currentTimeMillis()+" -- " + locations.get(i).getLat()+" - "+locations.get(i).getLon() );
        }
        System.out.println("AssignmentDetailActivity post location done");
        try {
            synchronized (mDriver)
            {
                for(int i=0;i<8;i++)
                {
                    mDriver.wait(30000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void runWithAutomobile() {

        sleep(3000);
        clickButtonSkip();
        sleep(timeDelay);
        clickConfirm();
//        sleep(timeDelay);
        clickStart();
//        sleep(timeDelay);
        clickConfirmation();
//        sleep(timeDelay);
//        clickClosePopupCancel();
//        sleep(timeDelay);
        atLoading();
        atUnLoading();
    }

    public void atLoading() {
        System.out.println("AssignmentDetailActivity atLoading");
        swipeScreen();
//        sleep(timeDelay);
        // ARRIVE
        clickArrive();
//        sleep(timeDelay);
        clickConfirmArrivalLoading();
//        sleep(timeDelay);
        // START HAND-OVER
        clickArrive();
//        sleep(timeDelay);
        // START Confirm Inspection
        clickArrive();
        sleep(timeDelay);
        signOnScreen();
//        sleep(timeDelay);
        saveSign();
    }

    public void atUnLoading() {
        System.out.println("AssignmentDetailActivity atUnLoading");
//        sleep(timeDelay);
        clickArrive();
//        sleep(timeDelay);
        clickConfirmArrivalUnloading();
//        sleep(timeDelay);
        //// START HAND-OVER
        clickArrive();
//        sleep(timeDelay);
        // START Confirm Inspection
        clickArrive();
//        sleep(timeDelay);
        // sign
        sleep(timeDelay);
        signOnScreen();
//        sleep(timeDelay);
        saveSign();
//        sleep(timeDelay);
        arriveAtBase();
//        sleep(timeDelay);
        clickConfirmArrivalUnloading();


    }

    /**
     * alway call
     */
    private void clickButtonSkip() {
        System.out.println("AssignmentDetailActivity SKIP");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/assignment_screen_skip"));
            for (MobileElement mobileElement : list) {

                if (mobileElement.getText().contains("SKIP")) {
                    mobileElement.click();
                    System.out.println("AssignmentDetailActivity SKIP click");
                    break;
                }

            }
            sleep(timeSleepLoop);
        }

    }

    private void clickConfirm() {
        System.out.println("AssignmentDetailActivity CONFIRM ");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_task_group_confirm"));
            if (list != null && list.size() > 0) {
                list.get(0).click();
                System.out.println("AssignmentDetailActivity CONFIRM Click");
                break;
            }
            sleep(timeSleepLoop);
        }


    }

    private void clickStart() {
        System.out.println("AssignmentDetailActivity clickStart");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_start_assignment"));
            for (MobileElement element : list) {
                if (element.getText().contains("START ASSIGNMENT")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickStart Click");
                    break;
                }
            }
            sleep(timeSleepLoop);
        }

    }

    private void clickConfirmation() {
        boolean isClick = false;
        System.out.println("AssignmentDetailActivity clickConfirmation");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_positive"));
            for (MobileElement element : list) {
                if (element.getText().contains("YES")) {
                    element.click();
                    isClick = true;
                    System.out.println("AssignmentDetailActivity clickConfirmation click");
//                    com.gear71.nightly.android:id/btn_positive
                    break;
                }
            }
            sleep(timeSleepLoop);
        }
        if (isClick) clickClosePopupCancel();

    }

    private void clickClosePopupCancel() {
        System.out.println("AssignmentDetailActivity clickClosePopup");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_neutral"));
            for (MobileElement element : list) {
                if (element.getText().contains("OK")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickClosePopup click ");
                    break;
                }
            }
            sleep(timeSleepLoop);
        }


    }

    private void swipeScreen() {
        System.out.println("AssignmentDetailActivity swipeScreen");
        List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/card_feeling_rate"));
        if (list != null && list.size() > 0) {
            new TouchAction(mDriver).press(PointOption.point(658, 720)).moveTo(PointOption.point(70, 720)).perform();
        }

    }

    private void clickArrive() {
        System.out.println("AssignmentDetailActivity clickArrive");
        for (int i = 0; i <loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_arrive"));
            for (MobileElement element : list) {

                if (element.getText().contains("ARRIVE")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickArrive ARRIVE");
                    break;
                }
                if (element.getText().contains("CONFIRM INSPECTION")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickArrive CONFIRM INSPECTION");
                    break;
                }
                if (element.getText().contains("CHECK INSPECTION")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickArrive CONFIRM INSPECTION");
                    break;
                }
                if (element.getText().contains("START HAND-OVER")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickArrive START HAND-OVER");
                    break;
                }

            }
            sleep(timeSleepLoop);
        }

    }

    private void clickConfirmArrivalLoading() {
        System.out.println("AssignmentDetailActivity clickConfirmArrival Loading");
        confirmArrival();
    }

    private void clickConfirmArrivalUnloading() {
        System.out.println("AssignmentDetailActivity clickConfirmArrival UnLoading");
        confirmArrival();
    }

    private void confirmArrival() {
        boolean click = false;
        System.out.println("AssignmentDetailActivity clickConfirmation");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_positive"));
            for (MobileElement element : list) {
                if (element.getText().contains("CONFIRM ARRIVAL")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity clickConfirmation Click");
                    click = true;
                    break;
                }
            }
            sleep(timeSleepLoop);
        }

        if (click == false) {
            System.out.println("AssignmentDetailActivity TouchAction onScreen");
            new TouchAction(mDriver).tap(PointOption.point(598, 1222)).perform();
        }
    }

    private void signOnScreen() {
        //com.gear71.nightly.android:id/asigSignatureCanvas
        new TouchAction(mDriver).longPress(PointOption.point(422, 435))
                .moveTo(PointOption.point(620, 428)).release().perform();
    }

    private void saveSign() {
        System.out.println("AssignmentDetailActivity saveSign");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/asigCompleteButton"));
            for (MobileElement element : list) {
                System.out.println("AssignmentDetailActivity saveSign" + list.size());
                if (element.getText().contains("SAVE")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity saveSign click");
                    break;
                }
            }
            sleep(timeSleepLoop);
        }

    }

    private void arriveAtBase() {
        System.out.println("AssignmentDetailActivity arriveAtBase");
        for (int i = 0; i < loopCount; i++) {
            List<MobileElement> list = mDriver.findElements(MobileBy.id("com.gear71.nightly.android:id/btn_arrive_base"));
            for (MobileElement element : list) {
                if (element.getText().contains("ARRIVE AT BASE")) {
                    element.click();
                    System.out.println("AssignmentDetailActivity arriveAtBase click");
                    break;
                }
            }
            sleep(timeSleepLoop);
        }

    }
}
