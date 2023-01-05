package rules.inefficient_apis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

class InefficientApis extends Activity {

    // {fact rule=inefficient-apis@v1.0 defects=1}
    Map<String, Object> copyMapNonCompliant(Map<String, Object> parameterMap) {
        // Noncompliant: map will be rehashed after 75% of all keys have been added to the map.
        Map<String, Object> map = new HashMap<String, Object>(parameterMap.size());
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    // {/fact}

    // {fact rule=inefficient-apis@v1.0 defects=0}
    Map<String, Object> copyMapCompliant(Map<String, Object> parameterMap) {
        // Compliant: map will not be rehashed because its expected size is provided.
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(parameterMap.size());
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    // {/fact}

    // {fact rule=inefficient-apis@v1.0 defects=1}
    public String getMessageNonCompliant(Bundle savedInstanceState) {
        Intent intent = getIntent();
        // Noncompliant: using Serializable to pass data between different Android components.
        return (String) intent.getSerializableExtra("message");
    }
    // {/fact}

    // {fact rule=inefficient-apis@v1.0 defects=0}
    public String getMessageCompliant(Bundle savedInstanceState) {
        Intent intent = getIntent();
        // Compliant: using Parcelable to pass data between different Android components.
        return intent.getParcelableExtra("message");
    }
    // {/fact}
}
