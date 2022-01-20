/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.improper_locking;

// {fact rule=improper-locking@v1.0 defects=0}
public class ImproperLockingCompliant {

    Object main = new Object();
    Object one = new Object();
    Object two = new Object();

    // Compliant: avoids deadlock by locking on the main.

    void firstMethod() {
        synchronized (main) {
            synchronized (one) {
                synchronized (two) {
                    System.out.println("Task Complete!!!\n");
                }
            }
        }
    }

    void secondMethod() {
        synchronized (main) {
            synchronized (two) {
                synchronized (one) {
                    System.out.println("Task Complete!!!\n");
                }
            }
        }
    }
}
// {/fact}
