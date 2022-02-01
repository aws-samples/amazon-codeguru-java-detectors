package rules.simplifiable_code;

import android.os.Parcel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

import stubs.Item;

class SimplifiableCode {

    Gson gson;

    // {fact rule=simplifiable-code@v1.0 defects=1}
    public List<String> getItemKeysNonCompliant(JsonObject message) {
        JsonArray items = message.getAsJsonArray("key");
        List<String> list = new ArrayList<>();
        // Noncompliant: JsonArray is deserialized to construct a list of items by iterating in a loop.
        for (JsonElement item : items) {
            list.add(gson.fromJson(item, String.class));
        }
        return list;
    }
    // {/fact}

    // {fact rule=simplifiable-code@v1.0 defects=0}
    public List<Item> getItemKeysCompliant(JsonObject message) {
        JsonArray items = message.getAsJsonArray("key");
        // Compliant: JsonArray is deserialized to construct a list of items without iterating in a loop.
        return gson.fromJson(items, new TypeToken<List<String>>(){}.getType());
    }
    // {/fact}

    // {fact rule=simplifiable-code@v1.0 defects=1}
    public String getItemNonCompliant(Parcel input) {
        try {
            // Noncompliant: output of readValue is type cast to String.
            return (String) input.readValue(String.class.getClassLoader());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // {/fact}

    // {fact rule=simplifiable-code@v1.0 defects=0}
    public String getItemCompliant(Parcel input) {
        try {
            // Compliant: readParcelable is used to read the Parcel input, which does not require an explicit type cast.
            return input.readParcelable(String.class.getClassLoader());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // {/fact}
}
