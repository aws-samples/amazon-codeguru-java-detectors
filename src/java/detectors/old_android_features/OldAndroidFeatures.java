package rules.old_android_features;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stubs.ViewHolder;

class OldAndroidFeatures extends Activity {

    int layoutResourceId = 0;

    // {fact rule=old-android-features@v1.0 defects=1}
    public ViewHolder onCreateViewHolderNonCompliant(ViewGroup parent, int viewType) {
        // Noncompliant: layout is created programmatically in code, without using the LayoutInflater.
        return new ViewHolder(new TextView(parent.getContext()));
    }
    // {/fact}

    // {fact rule=old-android-features@v1.0 defects=0}
    public ViewHolder onCreateViewHolderCompliant(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        // Compliant: LayoutInflater is used to inflate views.
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layoutResourceId, parent, false);
        return new ViewHolder(itemView);
    }
    // {/fact}
}
