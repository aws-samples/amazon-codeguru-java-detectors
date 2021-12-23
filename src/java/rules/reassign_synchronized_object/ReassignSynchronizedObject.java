/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.reassign_synchronized_object;

public class ReassignSynchronizedObject {
    Object mutex, mutex1;
    // {fact rule=reassign-synchronized-object@v1.0 defects=1}
    void assignSynchronizedObjectNonCompliant() {
        // Noncompliant: synchronized objects re-assigned in the same synchronized block.
        synchronized (mutex) {
            mutex = new Object();
            example(mutex);
        }
    }
    // {/fact}

    // {fact rule=reassign-synchronized-object@v1.0 defects=0}
    void assignSynchronizingObjectCompliant() {
        // Compliant: avoids re-assigning synchronizing objects in the same synchronized block.
        synchronized (mutex) {
            mutex1 = new Object();
            example(mutex);
        }
    }
    // {/fact}

    void example(Object mutex) {
        // do-something
    }
}
