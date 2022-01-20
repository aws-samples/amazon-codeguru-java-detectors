/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.iterate_on_map_entries;

import java.util.Map;


public class IterateOnMapEntries {

    // {fact rule=iterate-on-map-entries@v1.0 defects=1}
    public void iterateOnKeySetUsingValuesNoncompliant(Map<String, String> map) {
        // Noncompliant: iterate the set of keys and get the value of each key from the map.
        for (String name : map.keySet())
            System.out.println("Value: " + map.get(name));
    }
    // {/fact}

    // {fact rule=iterate-on-map-entries@v1.0 defects=0}
    public void iterateOnEntrySetCompliant(Map<String, String> map) {
        // Compliant: iterate the set of map entries.
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println("Key: " + entry.getKey() +
                    ", Value: " + entry.getValue());
    }
    // {/fact}
}
