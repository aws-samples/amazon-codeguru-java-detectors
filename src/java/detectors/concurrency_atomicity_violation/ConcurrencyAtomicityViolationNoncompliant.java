/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.concurrency_atomicity_violation;

import java.util.concurrent.ConcurrentHashMap;

// {fact rule=concurrency-atomicity-violation@v1.0 defects=1}
public class ConcurrencyAtomicityViolationNoncompliant {

    private ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();

    public void getValue(String key) {
        // Noncompliant: the key could be removed from the map between the first call and the second one.
        if (concurrentMap.containsKey(key)) {
            String value = concurrentMap.get(key);
            System.out.println(value.length());
        }
    }

    public void deleteValue(String key) {
        concurrentMap.remove(key);
    }
}
// {/fact}
