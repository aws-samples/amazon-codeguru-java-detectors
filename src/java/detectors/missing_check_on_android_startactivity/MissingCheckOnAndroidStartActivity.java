package rules.missing_check_on_android_startactivity;

import android.content.Context;
import android.content.Intent;

class MissingCheckOnAndroidStartActivity {

    // {fact rule=missing-check-on-android-startactivity@v1.0 defects=1}
    public void startActivityNonCompliant(Context context, Intent shareIntent) {
        // Noncompliant: there might be no application on the device to receive the implicit intent.
        context.startActivity(shareIntent);
    }
    // {/fact}

    // {fact rule=missing-check-on-android-startactivity@v1.0 defects=0}
    public boolean startActivityCompliant(Context context, Intent shareIntent) {
        if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
            // Compliant: called only if there is an application on the device to receive the implicit intent.
            context.startActivity(shareIntent);
            return true;
        }
        return false;
    }
    // {/fact}
}
