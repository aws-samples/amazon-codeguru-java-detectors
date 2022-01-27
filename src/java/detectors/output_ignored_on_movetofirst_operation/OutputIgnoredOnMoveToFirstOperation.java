package rules.output_ignored_on_movetofirst_operation;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

class OutputIgnoredOnMoveToFirstOperation {

    public static String getDataFromURINonCompliant(Context context, Uri uri) {
        String[] columns = { Columns.VALUE };
        try (Cursor cursor = context.getContentResolver().query(uri, columns, null, null, null)) {
            // Noncompliant: code does not check if the cursor is empty.
            cursor.moveToFirst();
            return cursor.getString(0);
        }
    }

    public static String getDataFromURICompliant(Context context, Uri uri) {
        String[] columns = { Columns.VALUE };
        try (Cursor cursor = context.getContentResolver().query(uri, columns, null, null, null)) {
            // Compliant: code handles the case when the cursor is empty.
            if (!cursor.moveToFirst()) {
                return null;
            }
            return cursor.getString(0);
        }
    }
}