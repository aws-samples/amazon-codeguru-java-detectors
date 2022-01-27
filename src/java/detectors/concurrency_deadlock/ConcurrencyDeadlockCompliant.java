/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.concurrency_deadlock;

// {fact rule=concurrency-deadlock@v1.0 defects=0}
public class ConcurrencyDeadlockCompliant {

    Object syncObject1 = new Object();
    Object syncObject2 = new Object();

    // Compliant: both methods request the two locks in the same order.

    public void compliantsync1() {
        synchronized (syncObject1) {
            synchronized (syncObject2) {
                System.out.println("Deadlock compliant example.");
                // Placeholder for code.
            }
        }
    }

    public void compliantsync2() {
        synchronized (syncObject1) {
            synchronized (syncObject2) {
                System.out.println("Deadlock compliant example.");
                // Placeholder for code.
            }
        }
    }
}
// {/fact}
