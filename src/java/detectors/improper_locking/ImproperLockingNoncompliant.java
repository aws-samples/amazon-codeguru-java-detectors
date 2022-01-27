/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.improper_locking;

// {fact rule=improper-locking@v1.0 defects=1}
public class ImproperLockingNoncompliant {

    Object one = new Object();
    Object two = new Object();

    // Noncompliant: results in a classic deadlock by requesting two objects in the opposite order.

    // firstDeadlockMethod has one and is waiting on two.
    void firstDeadlockMethod() {
        synchronized(one) {
            synchronized(two) {
                System.out.println("Task Complete!!!\n");
            }
        }
    }

    // secondDeadlockMethod has two and is waiting on one.
    void secondDeadlockMethod() {
        synchronized(two) {
            synchronized(one) {
                System.out.println("Task Complete!!!\n");
            }
        }
    }
}
// {/fact}
