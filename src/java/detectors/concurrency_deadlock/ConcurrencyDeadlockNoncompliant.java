/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.concurrency_deadlock;

// {fact rule=concurrency-deadlock@v1.0 defects=1}
public class ConcurrencyDeadlockNoncompliant {

    Object syncObject1 = new Object();
    Object syncObject2 = new Object();

    // Noncompliant: both methods request the same two locks in the opposite order, which can cause deadlock.

    public void noncompliantsync1() {
        synchronized (syncObject1) {
            synchronized (syncObject2) {
                System.out.println("Deadlock noncompliant example.");
                // Placeholder for code.
            }
        }
    }

    public void noncompliantsync2() {
        synchronized (syncObject2) {
            synchronized (syncObject1) {
                System.out.println("Deadlock noncompliant example.");
                // Placeholder for code.
            }
        }
    }
}
// {/fact}
