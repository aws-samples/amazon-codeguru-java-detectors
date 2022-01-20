/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.reassign_synchronized_object;

public class ReassignSynchronizedObject {
    Object mutex, mutex_one;
    // {fact rule=reassign-synchronized-object@v1.0 defects=1}
    private void assignSynchronizedObjectNoncompliant() {
        // Noncompliant: synchronized objects re-assigned in the same synchronized block.
        synchronized (mutex) {
            mutex = new Object();
            doSomething(mutex);
        }
    }
    // {/fact}

    // {fact rule=reassign-synchronized-object@v1.0 defects=0}
    private void assignSynchronizingObjectCompliant() {
        // Compliant: avoids re-assigning to synchronized objects in the same synchronized block.
        synchronized (mutex) {
            mutex_one = new Object();
            doSomething(mutex);
        }
    }
    // {/fact}

   private void doSomething(Object mutex) {
        // do-something
    }
}
