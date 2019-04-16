import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ActivityFactory {

    public static String PERMISSION = "com.android.packageinstaller.permission.ui.GrantPermissionsActivity";
    //    public static String SIGN_IN_ACTIVITY = "com.gear71.android.ui.screen.launch.SigninActivity";
    public static String SIGN_IN_ACTIVITY = "com.gear71.android.ui.activity.SignInActivity";
    public static String INTRODUCTION_ACTIVITY = "com.gear71.android.ui.activity.IntroductionActivity";
    public static String SIGN_IN_WITH_PASSWORD = "com.gear71.android.ui.activity.SignInWithPasswordActivity";
    public static String LAUNCHER_ACTIVITY = "com.gear71.android.ui.screen.launch.LaunchActivity";
    public static String TIMELINE_ACTIVITY = "com.gear71.android.ui.screen.timeline.TimelineActivity";
    public static String ASSIGNMENT_DETAIL_ACTIVITY = "com.gear71.android.ui.screen.assignment.AssignmentDetailActivity";

    public BaseActivity getActivity(String activity) {
//        String activity = driver.currentActivity();
        if (activity.contains(LAUNCHER_ACTIVITY)) {
        }
        if (activity.contains(PERMISSION)) {
        }
        if (activity.contains(TIMELINE_ACTIVITY))
            return new TimelineActivity();
        if (activity.contains(INTRODUCTION_ACTIVITY))
        if (activity.contains(ASSIGNMENT_DETAIL_ACTIVITY))
            return new AssignmentDetailActivity();
        return null;
    }
}
