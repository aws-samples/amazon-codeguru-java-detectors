/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.concurrency_over_synchronization;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrencyOverSynchronization {

    // {fact rule=concurrency-over-synchronization@v1.0 defects=1}
    public String putIfAbsentNoncompliant(ConcurrentHashMap<String, String> concurrentMap, String key) {
        String sampleValue = "sampleString";
        // Noncompliant: this is less efficient and more error-prone than using putIfAbsent.
        synchronized(this) {
            String value = concurrentMap.get(key);
            if (value == null) {
                concurrentMap.put(key, sampleValue);
            }
            return key;
        }
    }
    // {/fact}

    // {fact rule=concurrency-over-synchronization@v1.0 defects=0}
    public String putIfAbsentCompliant(ConcurrentHashMap<String, String> concurrentMap, String key) {
        String sampleValue = "sampleString";
        // Compliant: uses putIfAbsent instead of manual synchronization.
        concurrentMap.putIfAbsent(key, sampleValue);
        return key;
    }
    // {/fact}
}
