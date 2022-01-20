/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.concurrency_atomicity_violation;

import java.util.concurrent.ConcurrentHashMap;

// {fact rule=concurrency-atomicity-violation@v1.0 defects=0}
public class ConcurrencyAtomicityViolationCompliant {

    private ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();

    public void getValue(String key) {
        // Compliant: the value is checked for null before being accessed.
        String value = concurrentMap.get(key);
        if (value != null) {
            System.out.println(value.length());
        }
    }

    public void deleteValue(String key) {
        concurrentMap.remove(key);
    }
}
// {/fact}
