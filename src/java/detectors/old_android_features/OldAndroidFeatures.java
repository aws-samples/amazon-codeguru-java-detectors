package rules.old_android_features;

import android.app.Activity;
import android.content.Context;
import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class OldAndroidFeatures extends Activity {

    public ViewHolder onCreateViewHolderNonCompliant(ViewGroup parent, int viewType) {
        // Noncompliant: layout is created programmatically in code, without using the LayoutInflater.
        return new ViewHolder(new TextView(parent.getContext()));
    }

    public ViewHolder onCreateViewHolderCompliant(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        // Compliant: LayoutInflater is used to inflate views.
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }
}
