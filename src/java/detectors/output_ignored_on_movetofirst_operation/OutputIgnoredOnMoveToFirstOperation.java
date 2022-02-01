package rules.output_ignored_on_movetofirst_operation;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

class OutputIgnoredOnMoveToFirstOperation {

    // {fact rule=output-ignored-on-movetofirst-operation@v1.0 defects=1}
    public static String getDataFromURINonCompliant(Context context, Uri uri) {
        String[] columns = { "name", "address" };
        try (Cursor cursor = context.getContentResolver().query(uri, columns, null, null, null)) {
            // Noncompliant: code does not check if the cursor is empty.
            cursor.moveToFirst();
            return cursor.getString(0);
        }
    }
    // {/fact}

    // {fact rule=output-ignored-on-movetofirst-operation@v1.0 defects=0}
    public static String getDataFromURICompliant(Context context, Uri uri) {
        String[] columns = { "name", "address" };
        try (Cursor cursor = context.getContentResolver().query(uri, columns, null, null, null)) {
            // Compliant: code handles the case when the cursor is empty.
            if (!cursor.moveToFirst()) {
                return null;
            }
            return cursor.getString(0);
        }
    }
    // {/fact}
}
